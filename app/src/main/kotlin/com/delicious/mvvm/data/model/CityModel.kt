package com.delicious.mvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
        tableName = "city"
)
data class CityModel(
        @Json(name = "id")
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,

        @Json(name = "name")
        @ColumnInfo(name = "name")
        val name: String,

        @Json(name = "state")
        @ColumnInfo(name = "state")
        val state: String,

        @Json(name = "timezone_offset_hours")
        @ColumnInfo(name = "timezone_offset_hours")
        val timezone_offset_hours: Int?
)