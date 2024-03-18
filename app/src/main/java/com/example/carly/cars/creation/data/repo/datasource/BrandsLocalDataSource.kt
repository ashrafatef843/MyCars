package com.example.carly.cars.creation.data.repo.datasource

import com.example.carly.base.util.AssetReader
import com.example.carly.cars.creation.data.entity.BrandEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

const val BRANDS_JSON = "brands.json"
interface BrandsLocalDataSource {
    suspend fun getBrands(): List<BrandEntity>
}

class BrandsLocalDataSourceImpl
    @Inject
    constructor(
        private val assetReader: AssetReader,
        private val gson: Gson
    ) : BrandsLocalDataSource {
        override suspend fun getBrands(): List<BrandEntity> {
            val brandsStr = assetReader.readJSONFromAssets(BRANDS_JSON)
            return gson.fromJson(brandsStr, object : TypeToken<List<BrandEntity>>() {}.type)
        }
    }

@Module
@InstallIn(ViewModelComponent::class)
interface BrandsLocalDataSourceModule {
    @Binds
    fun provideBrandsLocalDataSource(brandsLocalDataSource: BrandsLocalDataSourceImpl): BrandsLocalDataSource
}
