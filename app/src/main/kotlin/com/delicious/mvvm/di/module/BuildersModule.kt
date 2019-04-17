package com.delicious.mvvm.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.delicious.mvvm.ui.FeatureActivity

@Module
abstract class BuildersModule {
  @ContributesAndroidInjector
  abstract fun contributeCryptocurrenciesActivity(): FeatureActivity
}