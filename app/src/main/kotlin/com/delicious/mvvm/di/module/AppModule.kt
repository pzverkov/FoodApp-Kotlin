package com.delicious.mvvm.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import dagger.Module
import dagger.Provides
import com.delicious.mvvm.persistance.dao.FeaturesDao
import com.delicious.mvvm.persistance.local.Database
import com.delicious.mvvm.ui.FeatureViewModelFactory
import com.delicious.mvvm.utils.Constants
import com.delicious.mvvm.utils.Utils
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
  companion object {
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
      override fun migrate(database: SupportSQLiteDatabase) {
        // Change the table name to the correct one
        database.execSQL("ALTER TABLE cryptocurrency RENAME TO cryptoCurrencies")
      }
    }
  }

  @Provides
  @Singleton
  fun provideApplication(): Application = app

  @Provides
  @Singleton
  fun provideCryptocurrenciesDatabase(app: Application): Database = Room.databaseBuilder(app,
      Database::class.java, Constants.DATABASE_NAME)
      /*.addMigrations(MIGRATION_1_2)*/
      .fallbackToDestructiveMigration()
      .build()

  @Provides
  @Singleton
  fun provideCryptocurrenciesDao(
      database: Database): FeaturesDao = database.featureDao()

  @Provides
  @Singleton
  fun provideCryptocurrenciesViewModelFactory(
      factory: FeatureViewModelFactory): ViewModelProvider.Factory = factory

  @Provides
  @Singleton
  fun provideUtils(): Utils = Utils(app)
}
