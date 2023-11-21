package pt.course.airbooking.presentation.flightinformation

import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentFlightInformationBinding
import pt.course.airbooking.presentation.base.BaseFragment

class FlightInformationFragment :
    BaseFragment<FragmentFlightInformationBinding, FlightInformationViewModel>(
        R.string.title_flight_information_screen,
        FlightInformationViewModel::class.java,
        FragmentFlightInformationBinding::inflate
    ) {
    companion object {

        @JvmStatic
        fun newInstance() = FlightInformationFragment()
    }
}