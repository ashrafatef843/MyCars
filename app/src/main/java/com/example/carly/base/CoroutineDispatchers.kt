package com.example.carly.base

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

interface CoroutineDispatchers {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
}

class CoroutineDispatchersImpl(
    override val IO: CoroutineDispatcher = Dispatchers.IO,
    override val Main: CoroutineDispatcher = Dispatchers.Main,
) : CoroutineDispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Singleton
    fun provideCoroutineScopeDispatchers(): CoroutineDispatchers =
        CoroutineDispatchersImpl()
}