package com.delicious.mvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
        tableName = "restaurant"
)
//TODO: Fill all the fields
data class RestaurantModel(
        @Json(name = "id")
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,

        @Json(name = "name")
        @ColumnInfo(name = "name")
        val name: String,

        @Json(name = "address")
        @ColumnInfo(name = "address")
        val address: String
)