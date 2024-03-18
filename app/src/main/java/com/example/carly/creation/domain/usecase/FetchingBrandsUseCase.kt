package com.example.carly.creation.domain.usecase

import com.example.carly.creation.domain.Brand
import com.example.carly.creation.domain.repo.BrandsRepo
import javax.inject.Inject

class FetchingBrandsUseCase
    @Inject
    constructor(
        private val brandsRepo: BrandsRepo,
    ) {
        suspend operator fun invoke(): List<Brand> = brandsRepo.getBrands()
    }
