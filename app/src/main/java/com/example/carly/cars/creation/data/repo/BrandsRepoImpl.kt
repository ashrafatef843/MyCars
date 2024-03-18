package com.example.carly.cars.creation.data.repo

import com.example.carly.cars.creation.data.entity.BrandMapper
import com.example.carly.cars.creation.data.repo.datasource.BrandsLocalDataSource
import com.example.carly.cars.creation.domain.Brand
import com.example.carly.cars.creation.domain.repo.BrandsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

class BrandsRepoImpl
    @Inject
    constructor(
        private val brandsLocalDataSource: BrandsLocalDataSource,
        private val brandMapper: BrandMapper,
    ) : BrandsRepo {
        override suspend fun getBrands(): List<Brand> =
            brandsLocalDataSource.getBrands()
                .map {
                    brandMapper.transform(it)
                }
    }

@Module
@InstallIn(ViewModelComponent::class)
interface BrandsRepoModule {
    @Binds
    fun provideBrandsRepo(brandsRepoImpl: BrandsRepoImpl): BrandsRepo
}
