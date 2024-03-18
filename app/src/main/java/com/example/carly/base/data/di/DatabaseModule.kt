package com.example.carly.base.data.di

import android.content.Context
import com.example.carly.base.data.database.CarlyDatabase
import com.example.carly.cars.data.repo.local.CarDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CarlyDatabase {
        return CarlyDatabase.buildDatabase(appContext)
    }

    @Provides
    fun provideChannelDao(carlyDatabase: CarlyDatabase): CarDao {
        return carlyDatabase.carDao()
    }
}
