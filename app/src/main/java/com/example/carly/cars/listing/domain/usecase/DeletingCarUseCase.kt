package com.example.carly.cars.listing.domain.usecase

import com.example.carly.cars.listing.domain.Car
import com.example.carly.cars.listing.domain.repo.CarRepo
import javax.inject.Inject

class DeletingCarUseCase @Inject constructor(
    private val carRepo: CarRepo
) {
    suspend operator fun invoke(car: Car) = carRepo.deleteCar(car)
}