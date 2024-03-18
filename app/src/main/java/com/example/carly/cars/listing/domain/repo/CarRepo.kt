package com.example.carly.cars.listing.domain.repo

import com.example.carly.cars.listing.domain.Car
import kotlinx.coroutines.flow.Flow

interface CarRepo {
    suspend fun addCar(car: Car)
    suspend fun selectAllCars(): Flow<List<Car>>
    suspend fun deleteCar(car: Car)
    suspend fun getCount(): Int
    suspend fun getSelectedCarFlow(): Flow<List<Car>>
    suspend fun getSelectedCar(): List<Car>
    suspend fun updateCar(car: Car)
}