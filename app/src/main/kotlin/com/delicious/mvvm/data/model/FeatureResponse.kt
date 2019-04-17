package com.delicious.mvvm.data.model

import com.squareup.moshi.Json

data class FeatureResponse(
        @Json(name = "type")
        val type: String? = null,
        @Json(name = "generated_at")
        val generated_at: String? = null,
        @Json(name = "date")
        val date: String? = null,
        @Json(name = "features")
        val features: List<FeatureModel>? = null
)