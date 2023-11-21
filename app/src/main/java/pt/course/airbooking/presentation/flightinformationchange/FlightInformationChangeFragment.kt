package pt.course.airbooking.presentation.flightinformationchange

import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentFlightInformationChangeBinding
import pt.course.airbooking.presentation.base.BaseFragment

class FlightInformationChangeFragment :
    BaseFragment<FragmentFlightInformationChangeBinding, FlightInformationChangeViewModel>(
        R.string.title_flight_information_change_screen,
        FlightInformationChangeViewModel::class.java,
        FragmentFlightInformationChangeBinding::inflate
    ) {
    companion object {
        @JvmStatic
        fun newInstance() = FlightInformationChangeFragment()
    }
}