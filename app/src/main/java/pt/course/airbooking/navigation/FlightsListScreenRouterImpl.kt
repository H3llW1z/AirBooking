package pt.course.airbooking.navigation

import com.github.terrakok.cicerone.Router
import pt.course.airbooking.presentation.flightslist.FlightsListScreenRouter
import javax.inject.Inject

class FlightsListScreenRouterImpl @Inject constructor(
    private val router: Router
) : FlightsListScreenRouter {
    override fun openFlightInfoScreen(id: Long) {
        val screen = getFlightInformationScreen(id)
        router.navigateTo(screen)
    }
}