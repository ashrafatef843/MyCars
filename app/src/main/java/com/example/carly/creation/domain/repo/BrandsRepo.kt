package com.example.carly.creation.domain.repo

import com.example.carly.creation.domain.Brand

interface BrandsRepo {
    suspend fun getBrands(): List<Brand>
}
