package com.example.carly.creation.data.entity

import com.example.carly.creation.domain.Brand
import com.example.carly.creation.domain.BrandSeries
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

class BrandMapper {
    fun transform(brandEntity: BrandEntity): Brand {
        return with(brandEntity) {
            Brand(
                name,
                series.map {
                    BrandSeries(
                        it.name,
                        it.minSupportedYear,
                        it.maxSupportedYear,
                        it.powerTypes,
                        it.supportedFeatures,
                    )
                },
            )
        }
    }
}

@InstallIn(ViewModelComponent::class)
@Module
class BrandMapperModule {
    @Provides
    fun provideBrandMapper() = BrandMapper()
}
