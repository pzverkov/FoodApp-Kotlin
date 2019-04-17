package com.delicious.mvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
        tableName = "neighborhood"
)
data class NeighborhoodModel (
        @Json(name = "id")
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String
)