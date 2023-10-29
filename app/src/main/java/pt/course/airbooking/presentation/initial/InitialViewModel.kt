package pt.course.airbooking.presentation.initial

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class InitialViewModel @Inject constructor(
    private val router: InitialScreenRouter
) : ViewModel() {

    fun openFlightsListScreen() {
        router.openFlightsListScreen()
    }

    fun openPlanesInfoScreen() {
        router.openPlanesInfoScreen()
    }

    fun openEditScreen() {
        router.openEditScreen()
    }
}