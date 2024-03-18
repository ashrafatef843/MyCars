package com.example.carly.cars.creation.domain

data class Brand(
    val name: String,
    val series: List<BrandSeries>,
)

data class BrandSeries(
    val name: String,
    val minSupportedYear: Int,
    val maxSupportedYear: Int,
    val powerTypes: List<String>,
    val supportedFeatures: List<String>,
)
