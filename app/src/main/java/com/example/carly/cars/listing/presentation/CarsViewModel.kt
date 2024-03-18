package com.example.carly.cars.listing.presentation

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carly.base.CoroutineDispatchers
import com.example.carly.base.presentation.BaseViewModel
import com.example.carly.cars.listing.domain.usecase.DeletingCarUseCase
import com.example.carly.cars.listing.domain.usecase.FetchingCarsUseCase
import com.example.carly.cars.listing.domain.usecase.SelectingCarUseCase
import com.example.carly.cars.listing.presentation.model.CarMapper
import com.example.carly.cars.listing.presentation.model.CarView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val fetchingCarsUseCase: FetchingCarsUseCase,
    private val deletingCarUseCase: DeletingCarUseCase,
    private val selectingCarUseCase: SelectingCarUseCase,
    private val dispatchers: CoroutineDispatchers,
    private val carMapper: CarMapper
) : BaseViewModel<CarsState>() {

    override fun getInitialState(): CarsState = CarsState()

    init {
        getCars()
    }

    private fun getCars() {
        viewModelScope.launch(dispatchers.Main) {
            fetchingCarsUseCase()
                .map {
                    it.map { car ->
                        carMapper.transform(car)
                    }
                }
                .collect { cars ->
                    setState {
                        it.copy(
                            cars = cars
                        )
                    }
                }
        }
    }

    fun deleteCar(carView: CarView) {
        viewModelScope.launch(dispatchers.Main) {
            deletingCarUseCase(carMapper.transform(carView))
        }
    }

    fun selectCar(carView: CarView) {
        viewModelScope.launch(dispatchers.Main) {
            selectingCarUseCase(
                carMapper.transform(carView)
            )
            setState {
                it.copy(
                    selectingCar = SelectingCarStatus.CarSelected
                )
            }
        }
    }
}

data class CarsState(
    val cars: List<CarView> = listOf(),
    val selectingCar: SelectingCarStatus = SelectingCarStatus.Uninitialized
)

sealed class SelectingCarStatus {
    object Uninitialized : SelectingCarStatus()
    object CarSelected : SelectingCarStatus()
}