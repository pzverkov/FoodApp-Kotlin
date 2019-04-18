package com.delicious.mvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "geometry"
)
data class GeometryModel(

    @Json(name = "type")
    @ColumnInfo(name = "type")
    val type: String
)