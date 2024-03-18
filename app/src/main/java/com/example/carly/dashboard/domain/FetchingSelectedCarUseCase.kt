package com.example.carly.dashboard.domain

import com.example.carly.cars.domain.repo.CarRepo
import javax.inject.Inject

class FetchingSelectedCarUseCase @Inject constructor(
    private val carRepo: CarRepo
) {
    suspend operator fun invoke() = carRepo.getSelectedCarFlow()
}
