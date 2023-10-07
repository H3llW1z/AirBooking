package pt.course.airbooking.domain.usecase

import pt.course.airbooking.domain.BookingRepository
import javax.inject.Inject

class GetPlaneTypeByIdUseCase @Inject constructor(private val bookingRepository: BookingRepository) {

    suspend operator fun invoke(id: Long) = bookingRepository.getPlaneTypeById(id)
}