package com.example.carly.cars.listing.domain

data class Car(
    val id: Int = 0,
    val brand: String,
    val series: String,
    val buildYear: Int,
    val powerType: String,
    val supportedFeatures: List<String>,
    val isSelected: Boolean
)
