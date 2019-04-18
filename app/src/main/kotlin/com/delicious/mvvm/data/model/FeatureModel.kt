package com.delicious.mvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
        tableName = "features"
)
data class FeatureModel(

        @Json(name = "id")
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        val id: String,

        @Json(name = "name")
        @ColumnInfo(name = "name")
        val name: String?,

        @Json(name = "type")
        @ColumnInfo(name = "type")
        val type: String,

        @Json(name = "priority")
        @ColumnInfo(name = "priority")
        val priority: String?,

        @Json(name = "is_featured")
        @ColumnInfo(name = "is_featured")
        val is_featured: Boolean,

        @Json(name = "date")
        @ColumnInfo(name = "date")
        val date: Long,

        @Json(name = "properties")
        @ColumnInfo(name = "properties")
        val properties: PropertiesModel,

        @Json(name = "geometry")
        @ColumnInfo(name = "geometry")
        val geometry: GeometryModel,

        @Json(name = "meal")
        @ColumnInfo(name = "meal")
        val meal: MealModel,

        @Json(name = "restaurant")
        @ColumnInfo(name = "restaurant")
        val restaurant: RestaurantModel,

        @ColumnInfo(name = "ordered")
        var ordered: Boolean? = false

) : Serializable
