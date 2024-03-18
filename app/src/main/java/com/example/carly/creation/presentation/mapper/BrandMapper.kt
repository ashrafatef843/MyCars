package com.example.carly.creation.presentation.mapper

import com.example.carly.creation.domain.Brand
import com.example.carly.creation.presentation.BrandSeriesView
import com.example.carly.creation.presentation.BrandView
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
