package pt.course.airbooking.navigation

import com.github.terrakok.cicerone.Router
import pt.course.airbooking.presentation.congratulations.CongratulationsScreenRouter
import javax.inject.Inject

class CongratulationsScreenRouterImpl @Inject constructor(
    private val router: Router
) : CongratulationsScreenRouter {
    override fun backToMenu() {
        val mainScreen = getInitialScreen()
        router.backTo(mainScreen)
    }
}