package com.example.carly.cars.creation.presentation.model

data class BrandView(
    val name: String,
    val series: List<BrandSeriesView>,
)

data class BrandSeriesView(
    val name: String,
    val minSupportedYear: Int,
    val maxSupportedYear: Int,
    val powerTypes: List<String>,
    val supportedFeatures: List<String>,
)
