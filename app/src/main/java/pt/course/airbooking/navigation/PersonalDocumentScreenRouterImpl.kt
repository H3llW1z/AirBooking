package pt.course.airbooking.navigation

import com.github.terrakok.cicerone.Router
import pt.course.airbooking.presentation.personaldocument.PersonalDocumentScreenRouter
import javax.inject.Inject

class PersonalDocumentScreenRouterImpl @Inject constructor(
    private val router: Router
): PersonalDocumentScreenRouter {
    override fun openCongratulationsScreen(bookingCode: String) {
        val congratulationsScreen = getCongratulationsScreen(bookingCode)
        router.navigateTo(congratulationsScreen)
    }
}