package pt.course.airbooking.presentation.flightinformation

import android.os.Bundle
import android.view.View
import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentFlightInformationBinding
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.entity.PlaneType
import pt.course.airbooking.presentation.base.BaseFragment
import pt.course.airbooking.presentation.base.State
import pt.course.airbooking.presentation.formatter.getDateFormatter
import pt.course.airbooking.presentation.formatter.getTimeFormatter
import java.util.Locale

class FlightInformationFragment :
    BaseFragment<FragmentFlightInformationBinding, FlightInformationViewModel>(
        R.string.title_flight_information_screen,
        FlightInformationViewModel::class.java,
        FragmentFlightInformationBinding::inflate
    ) {

    private var _flightId: Long? = null
    private val flightId get() = _flightId ?: throw RuntimeException("Flight id is null!")

    private fun parseArguments() {
        val args = requireArguments()
        if (!args.containsKey(FLIGHT_ID_EXTRA_KEY))
            throw RuntimeException("Flight id argument is absent.")
        _flightId = args.getLong(FLIGHT_ID_EXTRA_KEY)
    }

    companion object {

        private const val FLIGHT_ID_EXTRA_KEY = "flight_id_extra_key"

        @JvmStatic
        fun newInstance(id: Long) = FlightInformationFragment().apply {
            arguments = Bundle().apply {
                putLong(FLIGHT_ID_EXTRA_KEY, id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModel()
        viewModel.loadFlightInfo(flightId)
    }

    private fun setupClickListeners() {
        with(binding) {
            buttonBuy.setOnClickListener {
                viewModel.openBookingScreen(flightId)
            }
            buttonChange.setOnClickListener {
                viewModel.openFlightEditScreen(flightId)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Initial -> {}
                is State.EmptyResult -> {}
                is State.Content -> {
                    fillAllFields(state.data.first, state.data.second)
                }
            }
        }
    }

    private fun fillAllFields(flight: Flight, planeType: PlaneType) {
        val dateFormatter = getDateFormatter()
        val timeFormatter = getTimeFormatter()

        with(binding) {
            departureLocationTitle.text = flight.departureLocation
            destinationLocationTitle.text = flight.destinationLocation
            departureAirport.text = String.format(
                Locale.getDefault(),
                getString(R.string.aeroport_with_code_template),
                flight.departureAirport,
                flight.departureAirportCode
            )
            destinationAirport.text = String.format(
                Locale.getDefault(),
                getString(R.string.aeroport_with_code_template),
                flight.destinationAirport,
                flight.destinationAirportCode
            )
            departureDate.text = dateFormatter.format(flight.departureTimestamp)
            departureTime.text = timeFormatter.format(flight.departureTimestamp)

            destinationDate.text = dateFormatter.format(flight.arrivalTimestamp)
            destinationTime.text = timeFormatter.format(flight.arrivalTimestamp)

            planeName.text = planeType.name
            planeCapacity.text = String.format(
                Locale.getDefault(),
                getString(R.string.capacity_template),
                planeType.capacity
            )
            planeDescription.text = planeType.description
        }
    }

}