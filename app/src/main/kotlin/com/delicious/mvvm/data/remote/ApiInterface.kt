package com.delicious.mvvm.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import com.delicious.mvvm.data.model.FeatureResponse

interface ApiInterface {

  @GET("5b6379d5300000cd33650429.json/")
  fun getFeatures(): Observable<FeatureResponse>
}