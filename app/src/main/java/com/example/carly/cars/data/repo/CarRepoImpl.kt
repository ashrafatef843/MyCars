package com.example.carly.cars.data.repo

import com.example.carly.cars.data.repo.local.CarLocalDataSource
import com.example.carly.cars.data.repo.local.entity.CarMapper
import com.example.carly.cars.listing.domain.Car
import com.example.carly.cars.listing.domain.repo.CarRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarRepoImpl @Inject constructor(
    private val carLocalDataSource: CarLocalDataSource,
    private val carMapper: CarMapper
) : CarRepo {

    override suspend fun addCar(car: Car) {
        carLocalDataSource.addCar(carMapper.transform(car))
    }

    override suspend fun selectAllCars(): Flow<List<Car>> {
        return carLocalDataSource.selectAllCars()
            .map {
                it.map { carEntity ->
                    carMapper.transform(carEntity)
                }
            }
    }

    override suspend fun deleteCar(car: Car) {
        carLocalDataSource.deleteCar(
            carMapper.transform(car)
        )
    }

    override suspend fun getCount(): Int {
        return carLocalDataSource.getCount()
    }

    override suspend fun getSelectedCarFlow(): Flow<List<Car>> {
        return carLocalDataSource.getSelectedCarFlow()
            .map {
                it.map { carEntity ->
                    carMapper.transform(carEntity)
                }
            }
    }

    override suspend fun getSelectedCar(): List<Car> {
        return carLocalDataSource.getSelectedCar()
            .map { carEntity ->
                carMapper.transform(carEntity)
            }

    }

    override suspend fun updateCar(car: Car) {
        carLocalDataSource.updateCar(
            carMapper.transform(car)
        )
    }
}

@Module
@InstallIn(ViewModelComponent::class)
interface CarRepoModule {
    @Binds
    fun provideBCarRepo(carRepoImpl: CarRepoImpl): CarRepo
}
