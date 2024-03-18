package com.example.carly.cars.data.repo.local.entity

import com.example.carly.cars.listing.domain.Car
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

class CarMapper {
    fun transform(car: Car): CarEntity {
        return with(car) {
            CarEntity(
                id = id,
                brand = brand,
                series = series,
                buildYear = buildYear,
                powerType = powerType,
                supportedFeatures = supportedFeatures,
                isSelected = isSelected
            )
        }
    }

    fun transform(carEntity: CarEntity): Car {
        return with(carEntity) {
            Car(
                id = id,
                brand = brand,
                series = series,
                buildYear = buildYear,
                powerType = powerType,
                supportedFeatures = supportedFeatures,
                isSelected = isSelected
            )
        }
    }
}

@InstallIn(ViewModelComponent::class)
@Module
class CarMapperModule {
    @Provides
    fun provideCarMapper() = CarMapper()
}
