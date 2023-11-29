package pt.course.airbooking.presentation.flightinformationchange

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentFlightInformationChangeBinding
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.entity.PlaneType
import pt.course.airbooking.presentation.base.BaseFragment
import pt.course.airbooking.presentation.base.State
import pt.course.airbooking.presentation.formatter.getDateFormatter
import pt.course.airbooking.presentation.formatter.getTimeFormatter
import java.util.Locale

class FlightInformationChangeFragment :
    BaseFragment<FragmentFlightInformationChangeBinding, FlightInformationChangeViewModel>(
        R.string.title_flight_information_change_screen,
        FlightInformationChangeViewModel::class.java,
        FragmentFlightInformationChangeBinding::inflate
    ) {

    private var _flightId: Long? = null
    private val flightId get() = _flightId ?: throw RuntimeException("Flight id is null!")

    private var screenMode: Int = SCREEN_MODE_ADD

    private var departureTimeCalendar = GregorianCalendar(TimeZone.getDefault())
    private var arrivalTimeCalendar = GregorianCalendar(TimeZone.getDefault())
    private var selectedPlaneId = -1L

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
                    screenMode = SCREEN_MODE_ADD
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

        setupViews()
        observeViewModel()

        when (screenMode) {
            SCREEN_MODE_EDIT -> viewModel.getFlightAndPlanesInfo(flightId)
            SCREEN_MODE_ADD -> viewModel.getPlanesInfo()
            else -> throw RuntimeException("Unknown screen mode")
        }
    }

    private fun setupViews() {

        when (screenMode) {
            SCREEN_MODE_ADD -> binding.title.text = getString(R.string.flight_add_title)
            SCREEN_MODE_EDIT -> binding.title.text = getString(R.string.flight_information_change)
        }

        binding.departureTimeChange.setOnClickListener {
            TimePickerDialog(
                requireContext(),
                getTimeSetListener(departureTimeCalendar, binding.departureTimeChange),
                0,
                0,
                true
            ).show()
        }

        binding.departureDateChange.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                getDateSetListener(departureTimeCalendar, binding.departureDateChange),
                2023,
                1,
                1
            ).show()
        }

        binding.destinationTimeChange.setOnClickListener {
            TimePickerDialog(
                requireContext(),
                getTimeSetListener(arrivalTimeCalendar, binding.destinationTimeChange),
                0,
                0,
                true
            ).show()
        }

        binding.destinationDateChange.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                getDateSetListener(arrivalTimeCalendar, binding.destinationDateChange),
                2023,
                1,
                1
            ).show()
        }

        binding.autocompleteTextViewPlaneName

        binding.buttonSave.setOnClickListener {
            if (validateInputFields()) {
                with(binding) {
                    val departureLocation = editTextDepartureLocation.text.toString().trim()
                    val departureAirport = editTextDepartureAirport.text.toString().trim()
                    val departureAirportCode = editTextDepartureAirportCode.text.toString().trim()

                    val destinationLocation = editTextDestinationLocation.text.toString().trim()
                    val destinationAirport = editTextDestinationAirport.text.toString().trim()
                    val destinationAirportCode =
                        editTextDestinationAirportCode.text.toString().trim()

                    val departureTimestamp = departureTimeCalendar.timeInMillis
                    val arrivalTimestamp = arrivalTimeCalendar.timeInMillis

                    val planeTypeId = if (selectedPlaneId == -1L) 1 else selectedPlaneId

                    val flightId =
                        if (screenMode == SCREEN_MODE_EDIT) this@FlightInformationChangeFragment.flightId else 0

                    val flight = Flight(
                        id = flightId,
                        departureLocation = departureLocation,
                        departureAirport = departureAirport,
                        departureAirportCode = departureAirportCode,
                        destinationLocation = destinationLocation,
                        destinationAirport = destinationAirport,
                        destinationAirportCode = destinationAirportCode,
                        departureTimestamp = departureTimestamp,
                        arrivalTimestamp = arrivalTimestamp,
                        planeTypeId = planeTypeId
                    )
                    viewModel.saveFlight(flight)
                }
            }
        }
    }

    private fun getTimeSetListener(
        calendar: Calendar,
        timeView: TextView
    ): TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            timeView.text = getTimeFormatter().format(calendar.timeInMillis)
        }

    private fun getDateSetListener(
        calendar: Calendar,
        dateView: TextView
    ): DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            dateView.text = getDateFormatter().format(calendar.timeInMillis)
        }

    private fun validateInputFields(): Boolean {
        var isValid = true
        with(binding) {
            if (editTextDepartureLocation.text.isNullOrBlank()) {
                isValid = false
                editTextDepartureLocation.error = getString(R.string.required_field)
            }
            if (editTextDepartureAirport.text.isNullOrBlank()) {
                isValid = false
                editTextDepartureAirport.error = getString(R.string.required_field)
            }
            if (editTextDepartureAirportCode.text.isNullOrBlank()) {
                isValid = false
                editTextDepartureAirportCode.error = getString(R.string.required_field)
            }

            if (editTextDestinationLocation.text.isNullOrBlank()) {
                isValid = false
                editTextDestinationLocation.error = getString(R.string.required_field)
            }
            if (editTextDestinationAirport.text.isNullOrBlank()) {
                isValid = false
                editTextDestinationAirport.error = getString(R.string.required_field)
            }
            if (editTextDestinationAirportCode.text.isNullOrBlank()) {
                isValid = false
                editTextDestinationAirportCode.error = getString(R.string.required_field)
            }

            if (planeName.text.isNullOrBlank() || planeCapacity.text.isNullOrBlank() || planeDescription.text.isNullOrBlank()) {
                isValid = false
                Toast.makeText(
                    requireContext(),
                    getString(R.string.fill_plane_info_fields),
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (departureDateChange.text == getString(R.string.date_not_set) ||
                departureTimeChange.text == getString(R.string.time_not_set) ||
                destinationDateChange.text == getString(R.string.date_not_set) ||
                destinationTimeChange.text == getString(R.string.time_not_set)
            ) {
                isValid = false
                Toast.makeText(
                    requireContext(),
                    getString(R.string.set_departure_arrival_time),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return isValid
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

    private fun fillAllFields(flight: Flight?, planeTypes: List<PlaneType>) {

        val planeNames = planeTypes.map { it.name }
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, planeNames)
        with(binding) {
            autocompleteTextViewPlaneName.setAdapter(adapter)
            autocompleteTextViewPlaneName.threshold = 2
            autocompleteTextViewPlaneName.onItemClickListener =
                OnItemClickListener { _, _, position, _ ->
                    val name = adapter.getItem(position)
                    val planeType = planeTypes.find { it.name == name } ?: planeTypes[0]
                    planeName.text = planeType.name
                    planeCapacity.text = String.format(
                        Locale.getDefault(),
                        getString(R.string.capacity_template),
                        planeType.capacity
                    )
                    planeDescription.text = planeType.description
                    selectedPlaneId = planeType.id
                }
        }

        if (flight == null) {
            fillPlaneInfo(planeTypes[0])
            return
        }

        with(binding) {
            editTextDepartureLocation.setText(flight.departureLocation)
            editTextDepartureAirport.setText(flight.departureAirport)
            editTextDepartureAirportCode.setText(flight.departureAirportCode)

            editTextDestinationLocation.setText(flight.destinationLocation)
            editTextDestinationAirport.setText(flight.destinationAirport)
            editTextDestinationAirportCode.setText(flight.destinationAirportCode)

            departureTimeCalendar.timeInMillis = flight.departureTimestamp
            arrivalTimeCalendar.timeInMillis = flight.arrivalTimestamp

            val dateFormatter = getDateFormatter()
            val timeFormatter = getTimeFormatter()

            departureDateChange.text = dateFormatter.format(flight.departureTimestamp)
            departureTimeChange.text = timeFormatter.format(flight.departureTimestamp)

            destinationDateChange.text = dateFormatter.format(flight.arrivalTimestamp)
            destinationTimeChange.text = timeFormatter.format(flight.arrivalTimestamp)

            val planeType = planeTypes.find { it.id == flight.planeTypeId } ?: planeTypes[0]
            fillPlaneInfo(planeType)
        }
    }

    private fun fillPlaneInfo(planeType: PlaneType) {
        with(binding) {
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