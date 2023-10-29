package pt.course.airbooking.domain.usecase

import pt.course.airbooking.domain.BookingRepository
import pt.course.airbooking.domain.entity.Passport
import javax.inject.Inject

class BookTicketUseCase @Inject constructor(private val repository: BookingRepository) {

    suspend operator fun invoke(passport: Passport) = repository.bookTicket(passport)
}