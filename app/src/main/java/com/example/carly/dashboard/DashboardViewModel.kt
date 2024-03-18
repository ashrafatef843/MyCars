package com.example.carly.dashboard

import androidx.lifecycle.viewModelScope
import com.example.carly.base.CoroutineDispatchers
import com.example.carly.base.presentation.BaseViewModel
import com.example.carly.cars.domain.usecase.AddingCarUseCase
import com.example.carly.cars.presentation.model.CarMapper
import com.example.carly.cars.presentation.model.CarView
import com.example.carly.creation.domain.usecase.FetchingBrandsUseCase
import com.example.carly.creation.presentation.CarCreationState
import com.example.carly.creation.presentation.mapper.BrandMapper
import com.example.carly.dashboard.domain.FetchingSelectedCarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val carMapper: CarMapper,
    private val fetchingSelectedCarUseCase: FetchingSelectedCarUseCase
) : BaseViewModel<DashboardStatus>() {
    override fun getInitialState(): DashboardStatus = DashboardStatus()

    init {
        getSelectCar()
    }

    private fun getSelectCar() {
        viewModelScope.launch(dispatchers.Main) {
            fetchingSelectedCarUseCase()
                .map {
                    it.map { car ->
                        carMapper.transform(car)
                    }
                }
                .collect { cars ->
                    setState {
                        it.copy(
                            selectedCar =
                            if (cars.isEmpty())
                                CarStatus.EmptyDashboard
                            else
                                CarStatus.SelectedCar(cars[0])
                        )
                    }
                }
        }
    }
}

data class DashboardStatus(
    val selectedCar: CarStatus = CarStatus.Uninitialized
)

sealed class CarStatus {

    object Uninitialized : CarStatus()
    object EmptyDashboard : CarStatus()

    data class SelectedCar(
        val carView: CarView
    ) : CarStatus()
}