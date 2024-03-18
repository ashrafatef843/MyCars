package com.example.carly.cars.data.repo.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "series")
    val series: String,
    @ColumnInfo(name = "buildYear")
    val buildYear: Int,
    @ColumnInfo(name = "powerType")
    val powerType: String,
    @ColumnInfo(name = "supportedFeatures")
    val supportedFeatures: List<String>,
    @ColumnInfo(name = "isSelected")
    val isSelected: Boolean
)