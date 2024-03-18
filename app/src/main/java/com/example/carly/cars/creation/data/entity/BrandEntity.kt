package com.example.carly.cars.creation.data.entity

import androidx.annotation.Keep

@Keep
data class BrandEntity(
    val name: String,
    val series: List<BrandSeriesEntity>,
)

@Keep
data class BrandSeriesEntity(
    val name: String,
    val minSupportedYear: Int,
    val maxSupportedYear: Int,
    val powerTypes: List<String>,
    val supportedFeatures: List<String>,
)
