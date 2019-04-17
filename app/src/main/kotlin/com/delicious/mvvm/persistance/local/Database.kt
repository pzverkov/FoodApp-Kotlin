package com.delicious.mvvm.persistance.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.delicious.mvvm.data.model.Converters
import com.delicious.mvvm.data.model.FeatureModel
import com.delicious.mvvm.persistance.dao.FeaturesDao

@Database(entities = [(FeatureModel::class)], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
  abstract fun featureDao(): FeaturesDao
}