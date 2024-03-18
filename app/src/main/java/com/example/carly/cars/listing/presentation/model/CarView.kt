package com.example.carly.cars.listing.presentation.model

data class CarView(
    val id: Int = 0,
    val brand: String,
    val series: String,
    val buildYear: Int,
    val powerType: String,
    val supportedFeatures: List<String>,
    val isSelected: Boolean
)
