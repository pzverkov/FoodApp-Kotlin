package com.delicious.mvvm.data.model

import android.arch.persistence.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.Collections

class PropertiesModelListConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<PropertiesModel> {
        return if (data == null)
            emptyList()
        else
            gson.fromJson(data, object : TypeToken<List<PropertiesModel>>() {

            }.type)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<PropertiesModel>): String {
        return gson.toJson(someObjects)
    }
}
