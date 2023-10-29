package pt.course.airbooking.domain.entity

sealed class BookingResult {

    data class Success(val bookingCode: String): BookingResult()

    data object InvalidInput: BookingResult()
}
