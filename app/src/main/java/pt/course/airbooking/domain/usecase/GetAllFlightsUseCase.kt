package pt.course.airbooking.domain.usecase

import pt.course.airbooking.domain.BookingRepository
import javax.inject.Inject

class GetAllFlightsUseCase @Inject constructor(private val repository: BookingRepository) {
    suspend operator fun invoke() = repository.getAllFlights()
}