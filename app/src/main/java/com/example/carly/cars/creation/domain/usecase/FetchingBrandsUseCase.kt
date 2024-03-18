package com.example.carly.cars.creation.domain.usecase

import com.example.carly.cars.creation.domain.Brand
import com.example.carly.cars.creation.domain.repo.BrandsRepo
import javax.inject.Inject

class FetchingBrandsUseCase
    @Inject
    constructor(
        private val brandsRepo: BrandsRepo,
    ) {
        suspend operator fun invoke(): List<Brand> = brandsRepo.getBrands()
    }
