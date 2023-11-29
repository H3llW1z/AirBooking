package pt.course.airbooking.navigation

import com.github.terrakok.cicerone.Router
import pt.course.airbooking.presentation.initial.InitialScreenRouter
import javax.inject.Inject

class InitialScreenRouterImpl @Inject constructor(
    private val router: Router
) : InitialScreenRouter {
    override fun openFlightsListScreen() {
        router.navigateTo(getFlightsListScreen())
    }

    override fun openPlanesInfoScreen() {
        router.navigateTo(getPlanesListScreen())
    }
}