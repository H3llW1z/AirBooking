package pt.course.airbooking.presentation.congratulations

import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentCongratulationsBinding
import pt.course.airbooking.presentation.base.BaseFragment

class CongratulationsFragment :
    BaseFragment<FragmentCongratulationsBinding, CongratulationsViewModel>(
        R.string.title_congratulations_screen,
        CongratulationsViewModel::class.java,
        FragmentCongratulationsBinding::inflate
    ) {

    companion object {
        @JvmStatic
        fun newInstance() = CongratulationsFragment()
    }
}