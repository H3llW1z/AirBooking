package pt.course.airbooking.domain.usecase

import pt.course.airbooking.domain.BookingRepository
import pt.course.airbooking.domain.entity.Flight
import javax.inject.Inject

class AddFlightUseCase @Inject constructor(private val repository: BookingRepository) {
    suspend operator fun invoke(flight: Flight) = repository.addFlight(flight)
}