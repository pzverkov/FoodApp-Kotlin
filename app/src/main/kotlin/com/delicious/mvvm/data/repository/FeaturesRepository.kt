package com.delicious.mvvm.data.repository

import io.reactivex.Observable
import timber.log.Timber
import com.delicious.mvvm.data.model.FeatureModel
import com.delicious.mvvm.data.model.FeatureResponse
import com.delicious.mvvm.data.remote.ApiInterface
import com.delicious.mvvm.persistance.dao.FeaturesDao
import com.delicious.mvvm.utils.Utils
import javax.inject.Inject



class FeaturesRepository @Inject constructor(private val apiInterface: ApiInterface,
                                             private val featuresDao: FeaturesDao, private val utils: Utils) {

  fun getFeatures(): Observable<List<FeatureModel>> {
    val hasConnection = utils.isConnectedToInternet()
    var observableFromApi: Observable<FeatureResponse>? = null
    if (hasConnection) {
      observableFromApi = getFeaturesFromApi()
    }
    val observableFromDb = getFeaturesFromDb()

    //Observable.concatArrayEager(observableFromApi, observableFromDb) //Observable.just(observableFromApi?.singleElement()?.blockingGet()?.features)

    return if (hasConnection) Observable.just(observableFromApi?.singleElement()?.blockingGet()?.features)
    else observableFromDb
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