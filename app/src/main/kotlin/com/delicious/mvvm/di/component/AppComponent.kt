package com.delicious.mvvm.di.component

import com.delicious.mvvm.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import com.delicious.mvvm.di.module.AppModule
import com.delicious.mvvm.di.module.BuildersModule
import com.delicious.mvvm.di.module.NetModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidInjectionModule::class), (BuildersModule::class), (AppModule::class), (NetModule::class)]
)
interface AppComponent {
  fun inject(app: App)
}
