package pt.course.airbooking.domain.usecase

import pt.course.airbooking.domain.BookingRepository
import javax.inject.Inject

class GetFlightsByAirportCodeUseCase @Inject constructor (private val repository: BookingRepository) {

    suspend operator fun invoke(code: String) = repository.getFlightsByAirportCode(code)
}