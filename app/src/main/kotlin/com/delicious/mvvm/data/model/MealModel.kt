package com.delicious.mvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
        tableName = "meal"
)
data class MealModel(
        @Json(name = "id")
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,

        @Json(name = "name")
        @ColumnInfo(name = "name")
        val name: String,

        @Json(name = "description")
        @ColumnInfo(name = "description")
        val description: String,

        @Json(name = "cuisine")
        @ColumnInfo(name = "cuisine")
        val cuisine: String,

        @Json(name = "image_url")
        @ColumnInfo(name = "image_url")
        val image_url: String,

        @Json(name = "portion")
        @ColumnInfo(name = "portion")
        val portion: Int,

        @Json(name = "veg")
        @ColumnInfo(name = "veg")
        val veg: Boolean
)