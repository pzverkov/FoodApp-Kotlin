package com.delicious.mvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "geometry"
)
data class GeometryModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: String,

    @Json(name = "type")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "type")
    val type: String
)