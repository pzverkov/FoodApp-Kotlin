package com.delicious.mvvm.persistance.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single
import com.delicious.mvvm.data.model.FeatureModel

@Dao
interface FeaturesDao {

  @Query("SELECT * FROM features ORDER BY date")
  fun queryFeatures(): Single<List<FeatureModel>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertFeature(feature: FeatureModel)

  @Insert(
      onConflict = OnConflictStrategy.REPLACE
  )
  fun insertAllFeatures(features: List<FeatureModel>)
}