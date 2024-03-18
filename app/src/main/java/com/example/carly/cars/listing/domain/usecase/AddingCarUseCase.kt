package com.example.carly.cars.listing.domain.usecase

import com.example.carly.cars.listing.domain.Car
import com.example.carly.cars.listing.domain.repo.CarRepo
import javax.inject.Inject

class AddingCarUseCase @Inject constructor(
    private val carRepo: CarRepo
) {
    suspend operator fun invoke(car: Car) {
        val newCar = markAsSelectedIfFirst(car)
        carRepo.addCar(newCar)
    }

    private suspend fun markAsSelectedIfFirst(car: Car): Car {
        return if (carRepo.getCount() == 0)
            car.copy(isSelected = true)
        else
            car
    }
}
