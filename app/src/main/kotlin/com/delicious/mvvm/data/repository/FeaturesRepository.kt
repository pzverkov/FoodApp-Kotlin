package com.delicious.mvvm.data.repository

import com.delicious.mvvm.data.model.FeatureModel
import com.delicious.mvvm.data.model.FeatureResponse
import com.delicious.mvvm.data.remote.ApiInterface
import com.delicious.mvvm.persistance.dao.FeaturesDao
import com.delicious.mvvm.utils.Utils
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class FeaturesRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val featuresDao: FeaturesDao, private val utils: Utils
) {

    fun getFeatures(): Observable<List<FeatureModel>> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<FeatureResponse>? = null
        if (hasConnection) {
            observableFromApi = getFeaturesFromApi()
        }
        val observableFromDb = getFeaturesFromDb()

        return if (hasConnection)  Observable.just(observableFromApi?.subscribeOn(Schedulers.newThread())?.toList()?.blockingGet()?.get(0)?.features)
        else
        return observableFromDb
    }

    private fun getFeaturesFromApi(): Observable<FeatureResponse> {
        return apiInterface.getFeatures()
            .doOnNext {
                Timber.e(it.features?.size.toString())
                for (item in it.features!!) {
                    featuresDao.insertFeature(item)
                }
            }
    }

    private fun getFeaturesFromDb(): Observable<List<FeatureModel>> {
        return featuresDao.queryFeatures()
            .toObservable()
            .doOnNext {
                Timber.e(it.size.toString())
            }
    }
}