package com.example.carly.cars.creation.presentation.model

import com.example.carly.cars.creation.domain.Brand
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

class BrandMapper {
    fun transform(brand: Brand): BrandView {
        return with(brand) {
            BrandView(
                name,
                series.map {
                    BrandSeriesView(
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
