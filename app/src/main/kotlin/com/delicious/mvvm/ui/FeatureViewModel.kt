package com.delicious.mvvm.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import com.delicious.mvvm.data.model.FeatureModel
import com.delicious.mvvm.data.repository.FeaturesRepository
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class FeatureViewModel @Inject constructor(
    private val featuresRepository: FeaturesRepository) : ViewModel() {

  var featuresResult: MutableLiveData<List<FeatureModel>> = MutableLiveData()
  var featuresError: MutableLiveData<String> = MutableLiveData()
  var featuresLoader: MutableLiveData<Boolean> = MutableLiveData()
  private lateinit var disposableObserver: DisposableObserver<List<FeatureModel>>

  fun featuresResult(): LiveData<List<FeatureModel>> {
    return featuresResult
  }

  fun featuresError(): LiveData<String> {
    return featuresError
  }

  fun featuresLoader(): LiveData<Boolean> {
    return featuresLoader
  }

  fun loadFeatures(limit: Int, offset: Int) {

    disposableObserver = object : DisposableObserver<List<FeatureModel>>() {
      override fun onComplete() {

      }

      override fun onNext(features: List<FeatureModel>) {
        featuresResult.postValue(features)
        featuresLoader.postValue(false)
      }

      override fun onError(e: Throwable) {
        featuresError.postValue(e.message)
        featuresLoader.postValue(false)
      }
    }

    featuresRepository.getFeatures()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .debounce(400, MILLISECONDS) //NO often than 400ms!
        .subscribe(disposableObserver)
  }

  fun disposeElements() {
    if (!disposableObserver.isDisposed) disposableObserver.dispose()
  }

}