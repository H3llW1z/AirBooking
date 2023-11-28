package pt.course.airbooking.presentation.flightslist

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.view.inputmethod.EditorInfo
import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentFlightsListBinding
import pt.course.airbooking.presentation.base.BaseFragment
import pt.course.airbooking.presentation.base.State
import pt.course.airbooking.presentation.flightslist.adapter.FlightsListAdapter

class FlightsListFragment : BaseFragment<FragmentFlightsListBinding, FlightsListViewModel>(
    R.string.title_flights_list_screen,
    FlightsListViewModel::class.java,
    FragmentFlightsListBinding::inflate
) {

    companion object {

        @JvmStatic
        fun newInstance() = FlightsListFragment()
    }

    private val listAdapter = FlightsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
        viewModel.getAllFlights()
    }

    private fun setupUI() {
        with(binding) {

            listAdapter.onItemClick = {
                viewModel.openFlightInfoScreen(it)
            }

            recyclerViewFlights.adapter = listAdapter

            val lengthFilter = InputFilter.LengthFilter(3)

            val charactersFilter = InputFilter { source, start, end, _, _, _ ->
                for (i in start until end) {
                    if (source[i] !in 'A'..'Z' && source[i] !in 'a'..'z') {
                        return@InputFilter ""
                    }
                }
                return@InputFilter source
            }

            editTextAirportCode.filters = arrayOf(lengthFilter, charactersFilter)

            editTextAirportCode.setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val airportCode = view.text.toString().trim()
                    if (airportCode.isNotBlank()) {
                        viewModel.getFlightsByAirportCode(airportCode)
                    }
                }
                false
            }
            fabAddFlight.setOnClickListener {
                viewModel.openNewFlightScreen()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Initial -> {}
                is State.EmptyResult -> {
                    binding.recyclerViewFlights.visibility = View.GONE
                    binding.textViewNoData.visibility = View.VISIBLE
                }

                is State.Content -> {
                    val flightsList = state.data
                    listAdapter.submitList(flightsList)
                    binding.textViewNoData.visibility = View.GONE
                    binding.recyclerViewFlights.visibility = View.VISIBLE
                }

            }
        }
    }
}