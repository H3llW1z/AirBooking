package pt.course.airbooking.navigation

import com.github.terrakok.cicerone.Router
import pt.course.airbooking.presentation.flightinformationchange.FlightInformationChangeRouter
import javax.inject.Inject

class FlightInformationChangeRouterImpl @Inject constructor(
    private val router: Router
): FlightInformationChangeRouter {
    override fun goBack() {
        router.exit()
    }
}