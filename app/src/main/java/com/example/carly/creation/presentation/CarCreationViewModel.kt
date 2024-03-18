package com.example.carly.creation.presentation

import androidx.lifecycle.viewModelScope
import com.example.carly.base.CoroutineDispatchers
import com.example.carly.base.presentation.BaseViewModel
import com.example.carly.cars.domain.Car
import com.example.carly.cars.domain.usecase.AddingCarUseCase
import com.example.carly.creation.domain.usecase.FetchingBrandsUseCase
import com.example.carly.creation.presentation.mapper.BrandMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarCreationViewModel @Inject constructor(
    private val fetchingBrandsUseCase: FetchingBrandsUseCase,
    private val dispatchers: CoroutineDispatchers,
    private val brandMapper: BrandMapper,
    private val addingCarUseCase: AddingCarUseCase
) : BaseViewModel<CarCreationState>() {
    init {
        getBrands()
    }

    override fun getInitialState(): CarCreationState = CarCreationState()
    private fun getBrands() {
        viewModelScope.launch(dispatchers.Main) {
            val brands = fetchingBrandsUseCase()
            setState { currentState ->
                currentState.copy(
                    brands = brands.map {
                        brandMapper.transform(it)
                    }
                )
            }
            changeStep(true)
        }
    }

    fun onItemSelected(item: String, index: Int) {
        when (state.creationStep) {
            is CarCreationStep.BrandStep -> {
                onBrandSelected(item, index)
            }
            is CarCreationStep.SeriesStep -> {
                onSeriesSelected(item, index)
            }
            is CarCreationStep.YearStep -> {
                onYearSelected(item)
            }
            is CarCreationStep.PowerStep -> {
               onPowerSelected(item)
            }
            else -> {
                // do nothing
            }
        }
    }
    private fun onBrandSelected(brand: String, index: Int) {
        setState {
            it.copy(
                selectedBrand = brand,
                selectedBrandIndex = index
            )
        }
        changeStep(true)
    }

    private fun onSeriesSelected(series: String, index: Int) {
        setState {
            it.copy(
                selectedSeries = series,
                selectedSeriesIndex = index
            )
        }
        changeStep(true)
    }

    private fun onYearSelected(year: String) {
        setState {
            it.copy(
                selectedYear = year
            )
        }
        changeStep(true)
    }

    private fun onPowerSelected(power: String) {
        setState {
            it.copy(
                selectedPowerType = power
            )
        }
        changeStep(true)
    }

    fun onBackPress() {
        changeStep(false)
    }

    private fun changeStep(isMovingForward: Boolean) {
        when (state.creationStep) {
            is CarCreationStep.Uninitialized -> {
                handleUninitializedStep(isMovingForward)
            }
            is CarCreationStep.BrandStep -> {
                handleBrandStep(isMovingForward)
            }
            is CarCreationStep.SeriesStep -> {
                handleSeriesStep(isMovingForward)
            }
            is CarCreationStep.YearStep -> {
                handleYearStep(isMovingForward)
            }
            is CarCreationStep.PowerStep -> {
                handlePowerStep(isMovingForward)
            }
            else -> {
                // do nothing
            }
        }
    }

    private fun handleUninitializedStep(isMovingForward: Boolean) {
        if (isMovingForward)
            setState {
                val items = it.brands.map { brand -> brand.name }
                it.copy(
                    selectionItems = items,
                    filteredSelectionItems = items,
                    filteredText = "",
                    creationStep = CarCreationStep.BrandStep
                )
            }
        else
            setState {
                it.copy(
                    selectionItems = listOf(),
                    creationStep = CarCreationStep.CloseScreen
                )
            }
    }

    private fun handleBrandStep(isMovingForward: Boolean) {
        if (isMovingForward)
            setState {
                val items = it.brands[it.selectedBrandIndex]
                    .series
                    .map { series ->
                        series.name
                    }
                it.copy(
                    selectionItems = items,
                    filteredSelectionItems = items,
                    filteredText = "",
                    creationStep = CarCreationStep.SeriesStep
                )
            }
        else {
            handleUninitializedStep(false)
        }
    }

    private fun handleSeriesStep(isMovingForward: Boolean) {
        if (isMovingForward) {
            val selectedSeries = state.brands[state.selectedBrandIndex]
                .series[state.selectedSeriesIndex]
            setState {
                val items = createNumbers(
                    selectedSeries.minSupportedYear,
                    selectedSeries.maxSupportedYear
                )
                it.copy(
                    selectionItems = items,
                    filteredSelectionItems = items,
                    filteredText = "",
                    creationStep = CarCreationStep.YearStep
                )
            }
        } else {
            setState {
                it.copy(
                    selectedBrand = "",
                    selectedBrandIndex = -1
                )
            }
            handleUninitializedStep(true)
        }
    }

    private fun handleYearStep(isMovingForward: Boolean) {
        if (isMovingForward) {
            val selectedSeries = state.brands[state.selectedBrandIndex]
                .series[state.selectedSeriesIndex]
            setState {
                it.copy(
                    selectionItems =  selectedSeries.powerTypes,
                    filteredSelectionItems = selectedSeries.powerTypes,
                    filteredText = "",
                    creationStep = CarCreationStep.PowerStep
                )
            }
        } else {
            setState {
                it.copy(
                    selectedSeries = "",
                    selectedSeriesIndex = -1
                )
            }
            handleBrandStep(true)
        }
    }

    private fun handlePowerStep(isMovingForward: Boolean) {
        if (isMovingForward) {
           createCar()
        } else {
            setState {
                it.copy(
                    selectedYear = ""
                )
            }
            handleSeriesStep(true)
        }
    }

    private fun createCar() {
        viewModelScope.launch(dispatchers.Main) {
            with(state) {
                addingCarUseCase(
                    Car(
                        brand = selectedBrand,
                        series = selectedSeries,
                        buildYear = selectedYear.toInt(),
                        powerType = selectedPowerType,
                        supportedFeatures =
                        brands[selectedBrandIndex].series[selectedSeriesIndex].supportedFeatures,
                        isSelected = false
                    )
                )
            }
            setState {
                it.copy(
                    creationStep = CarCreationStep.CloseScreen
                )
            }
        }
    }

    fun searchFor(text: String) {
        setState {
            it.copy(
                filteredText = text,
                filteredSelectionItems = it.selectionItems.filter { item ->
                    item.contains(text, true)
                }
            )
        }
    }

    private fun createNumbers(from: Int, to: Int): List<String> {
        val list = mutableListOf<String>()
        for (i in from..to) {
            list.add(i.toString())
        }
        return list
    }
}

data class CarCreationState(
    val filteredText: String = "",
    val filteredSelectionItems: List<String> = listOf(),
    val selectionItems: List<String> = listOf(),
    val brands: List<BrandView> = listOf(),
    val creationStep: CarCreationStep = CarCreationStep.Uninitialized,
    val selectedBrand: String = "",
    val selectedBrandIndex: Int = -1,
    val selectedSeries: String = "",
    val selectedSeriesIndex: Int = -1,
    val selectedYear: String = "",
    val selectedPowerType: String = "",
    val supportedFeatures: List<String>? = null,
)

sealed class CarCreationStep {
    object Uninitialized : CarCreationStep()

    object BrandStep : CarCreationStep()

    object SeriesStep : CarCreationStep()

    object YearStep : CarCreationStep()

    object PowerStep : CarCreationStep()
    object CloseScreen : CarCreationStep()
}
