package pt.course.airbooking.presentation.flightinformationchange

import android.os.Bundle
import android.view.View
import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentFlightInformationChangeBinding
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.presentation.base.BaseFragment
import pt.course.airbooking.presentation.base.State

class FlightInformationChangeFragment :
    BaseFragment<FragmentFlightInformationChangeBinding, FlightInformationChangeViewModel>(
        R.string.title_flight_information_change_screen,
        FlightInformationChangeViewModel::class.java,
        FragmentFlightInformationChangeBinding::inflate
    ) {

    private var _flightId: Long? = null
    private val flightId get() = _flightId ?: throw RuntimeException("Flight id is null!")

    private var screenMode: Int = SCREEN_MODE_ADD

    private fun parseArguments() {
        val args = requireArguments()
        if (args.containsKey(SCREEN_MODE_EXTRA_KEY)) {
            when (args.getInt(SCREEN_MODE_EXTRA_KEY)) {
                SCREEN_MODE_EDIT -> {
                    if (args.containsKey(FLIGHT_ID_EXTRA_KEY)) {
                        screenMode = SCREEN_MODE_EDIT
                        _flightId = args.getLong(FLIGHT_ID_EXTRA_KEY)
                    } else {
                        throw RuntimeException("Flight id is absent")
                    }
                }
                SCREEN_MODE_ADD -> {
                    //nothing to do
                }
            }
        } else {
            throw RuntimeException("Screen mode is absent")
        }
    }
    companion object {

        private const val FLIGHT_ID_EXTRA_KEY = "flight_id_extra_key"
        private const val SCREEN_MODE_EXTRA_KEY = "screen_mode_extra_key"
        private const val SCREEN_MODE_ADD = 1
        private const val SCREEN_MODE_EDIT = 2
        @JvmStatic
        fun newInstance() = FlightInformationChangeFragment().apply {
            arguments = Bundle().apply {
                putInt(SCREEN_MODE_EXTRA_KEY, SCREEN_MODE_ADD)
            }
        }

        @JvmStatic
        fun newInstance(flightId: Long) = FlightInformationChangeFragment().apply {
            arguments = Bundle().apply {
                putInt(SCREEN_MODE_EXTRA_KEY, SCREEN_MODE_EDIT)
                putLong(FLIGHT_ID_EXTRA_KEY, flightId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO("setup views")
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Initial -> {}
                is State.EmptyResult -> {}
                is State.Content -> {
                    if (screenMode == SCREEN_MODE_EDIT) {
                        fillAllFields(state.data)
                    }
                }
            }
        }
    }

    private fun fillAllFields(flight: Flight) {
        //TODO()
    }
}