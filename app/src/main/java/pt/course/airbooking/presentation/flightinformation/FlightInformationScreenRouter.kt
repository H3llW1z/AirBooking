package pt.course.airbooking.presentation.flightinformation

interface FlightInformationScreenRouter {

    fun goBack()

    fun openBookingScreen(flightId: Long)

    fun openFlightEditScreen(flightId: Long)
}