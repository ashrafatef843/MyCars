package com.example.carly.cars.creation.domain.repo

import com.example.carly.cars.creation.domain.Brand

interface BrandsRepo {
    suspend fun getBrands(): List<Brand>
}
