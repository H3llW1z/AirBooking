package pt.course.airbooking.presentation.flightslist

import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentFlightsListBinding
import pt.course.airbooking.presentation.base.BaseFragment

class FlightsListFragment : BaseFragment<FragmentFlightsListBinding, FlightsListViewModel>(
    R.string.title_flights_list_screen,
    FlightsListViewModel::class.java,
    FragmentFlightsListBinding::inflate
) {

    companion object {

        @JvmStatic
        fun newInstance() = FlightsListFragment()
    }
}