package com.delicious.mvvm.data.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun stringPMList(data: String?): List<PropertiesModel> {
        return if (data == null)
            emptyList()
        else
            gson.fromJson(data, object : TypeToken<List<PropertiesModel>>() {

            }.type)
    }

    @TypeConverter
    fun somePMListToString(someObjects: List<PropertiesModel>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun somePMToString(someObjects: PropertiesModel): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun someGeometryToString(someObjects: GeometryModel): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun someMealToString(someObjects: MealModel): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun someRestaurantToString(someObjects: RestaurantModel): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringPM(data: String?): PropertiesModel {
        return gson.fromJson<PropertiesModel>(data, PropertiesModel::class.java)
    }

    @TypeConverter
    fun stringGM(data: String?): GeometryModel {
        return gson.fromJson<GeometryModel>(data, GeometryModel::class.java)
    }

    @TypeConverter
    fun stringMM(data: String?): MealModel {
        return gson.fromJson<MealModel>(data, MealModel::class.java)
    }

    @TypeConverter
    fun stringRM(data: String?): RestaurantModel {
        return gson.fromJson<RestaurantModel>(data, RestaurantModel::class.java)
    }
}