package pt.course.airbooking.domain.usecase

import pt.course.airbooking.domain.BookingRepository
import javax.inject.Inject

class GetFlightByIdUseCase @Inject constructor(
    private val repository: BookingRepository
) {
    suspend operator fun invoke(id: Long) = repository.getFlightById(id)
}