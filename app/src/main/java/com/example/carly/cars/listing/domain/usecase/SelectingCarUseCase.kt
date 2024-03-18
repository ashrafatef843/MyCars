package com.example.carly.cars.listing.domain.usecase

import com.example.carly.cars.listing.domain.Car
import com.example.carly.cars.listing.domain.repo.CarRepo
import javax.inject.Inject

class SelectingCarUseCase @Inject constructor(
    private val carRepo: CarRepo
) {
    suspend operator fun invoke(newCar: Car) {
        val selectedCars = carRepo
            .getSelectedCar()
        if (selectedCars.isNotEmpty())
            carRepo.updateCar(
                selectedCars[0].copy(isSelected = false)
            )
        carRepo.updateCar(
            newCar.copy(isSelected = true)
        )
    }
}