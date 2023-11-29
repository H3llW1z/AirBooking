package pt.course.airbooking.navigation

import com.github.terrakok.cicerone.Router
import pt.course.airbooking.presentation.flightinformation.FlightInformationScreenRouter
import javax.inject.Inject

class FlightInformationScreenRouterImpl @Inject constructor(
    private val router: Router
) : FlightInformationScreenRouter {

    override fun goBack() {
        router.exit()
    }

    override fun openBookingScreen(flightId: Long) {
        val documentScreen = getPersonalDocumentScreen(flightId)
        router.navigateTo(documentScreen)
    }

    override fun openFlightEditScreen(flightId: Long) {
        val flightEditScreen = getFlightInformationChangeScreen(flightId)
        router.navigateTo(flightEditScreen)
    }
}