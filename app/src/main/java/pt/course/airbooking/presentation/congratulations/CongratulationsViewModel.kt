package pt.course.airbooking.presentation.congratulations

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CongratulationsViewModel @Inject constructor(
    private val congratulationsScreenRouter: CongratulationsScreenRouter
) : ViewModel() {


    fun goBack() {
        congratulationsScreenRouter.backToMenu()
    }
}