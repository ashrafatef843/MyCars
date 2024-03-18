package com.example.carly.cars.data.repo.local

import com.example.carly.base.CoroutineDispatchers
import com.example.carly.cars.data.repo.local.entity.CarEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CarLocalDataSource {
    suspend fun addCar(carEntity: CarEntity)

    suspend fun selectAllCars(): Flow<List<CarEntity>>

    suspend fun deleteCar(carEntity: CarEntity)

    suspend fun getCount(): Int

    suspend fun getSelectedCarFlow(): Flow<List<CarEntity>>
    suspend fun getSelectedCar(): List<CarEntity>

    suspend fun updateCar(carEntity: CarEntity)
}

class CarLocalDataSourceImpl @Inject constructor(
    private val carDao: CarDao,
    private val coroutineDispatchers: CoroutineDispatchers
) : CarLocalDataSource {
    override suspend fun addCar(carEntity: CarEntity) {
        withContext(coroutineDispatchers.IO) {
            carDao.insert(carEntity)
        }
    }

    override suspend fun selectAllCars(): Flow<List<CarEntity>> {
        return carDao.getAll()
            .flowOn(coroutineDispatchers.IO)
    }

    override suspend fun deleteCar(carEntity: CarEntity) {
        withContext(coroutineDispatchers.IO) {
            carDao.delete(carEntity)
        }
    }

    override suspend fun getCount(): Int {
        return withContext(coroutineDispatchers.IO) {
            carDao.getCount()
        }
    }

    override suspend fun getSelectedCarFlow(): Flow<List<CarEntity>> {
        return carDao.getSelectedFlow()
            .flowOn(coroutineDispatchers.IO)
    }

    override suspend fun getSelectedCar(): List<CarEntity> {
        return withContext(coroutineDispatchers.IO) {
            carDao.getSelected()
        }
    }

    override suspend fun updateCar(carEntity: CarEntity) {
        return withContext(coroutineDispatchers.IO) {
            carDao.update(carEntity)
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
interface CarLocalDataSourceModule {
    @Binds
    fun provideCarLocalDataSource(carLocalDataSourceImpl: CarLocalDataSourceImpl): CarLocalDataSource
}
