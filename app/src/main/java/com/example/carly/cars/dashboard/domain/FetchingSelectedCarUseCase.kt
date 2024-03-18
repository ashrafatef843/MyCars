package com.example.carly.cars.dashboard.domain

import com.example.carly.cars.listing.domain.repo.CarRepo
import javax.inject.Inject

class FetchingSelectedCarUseCase @Inject constructor(
    private val carRepo: CarRepo
) {
    suspend operator fun invoke() = carRepo.getSelectedCarFlow()
}
