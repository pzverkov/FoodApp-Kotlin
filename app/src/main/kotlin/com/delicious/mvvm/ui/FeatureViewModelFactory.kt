package com.delicious.mvvm.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class FeatureViewModelFactory @Inject constructor(
    private val featureViewModel: FeatureViewModel) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(FeatureViewModel::class.java)) {
      return featureViewModel as T
    }
    throw IllegalArgumentException("Unknown class name")
  }
}