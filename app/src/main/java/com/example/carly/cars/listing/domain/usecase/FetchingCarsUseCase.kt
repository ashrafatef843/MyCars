package com.example.carly.cars.listing.domain.usecase

import com.example.carly.cars.listing.domain.Car
import com.example.carly.cars.listing.domain.repo.CarRepo
import javax.inject.Inject

class FetchingCarsUseCase @Inject constructor(
    private val carRepo: CarRepo
) {
    suspend operator fun invoke() = carRepo.selectAllCars()
}