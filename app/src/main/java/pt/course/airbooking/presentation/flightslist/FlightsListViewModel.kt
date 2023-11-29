package pt.course.airbooking.presentation.flightslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.usecase.GetAllFlightsUseCase
import pt.course.airbooking.domain.usecase.GetFlightsByAirportCodeUseCase
import pt.course.airbooking.domain.usecase.RemoveFlightByIdUseCase
import pt.course.airbooking.presentation.base.State
import javax.inject.Inject

class FlightsListViewModel @Inject constructor(
    private val getAllFlightsUseCase: GetAllFlightsUseCase,
    private val getFlightsByAirportCodeUseCase: GetFlightsByAirportCodeUseCase,
    private val removeFlightByIdUseCase: RemoveFlightByIdUseCase,
    private val flightsListScreenRouter: FlightsListScreenRouter
) : ViewModel() {

    private val _state: MutableLiveData<State<List<Flight>>> = MutableLiveData(State.Initial)
    val state: LiveData<State<List<Flight>>>
        get() = _state

    fun getAllFlights() {
        viewModelScope.launch {
            val result = getAllFlightsUseCase()
            _state.value = if (result.isEmpty()) {
                State.EmptyResult
            } else {
                State.Content(result)
            }
        }
    }

    fun getFlightsByAirportCode(code: String) {
        val upperCaseCode = code.uppercase()
        viewModelScope.launch {
            val result = getFlightsByAirportCodeUseCase(upperCaseCode)
            _state.value = if (result.isEmpty()) {
                State.EmptyResult
            } else {
                State.Content(result)
            }
        }
    }

    fun removeFlightById(flightId: Long) {
        viewModelScope.launch {
            removeFlightByIdUseCase(flightId)
            getAllFlights()
        }
    }

    fun openFlightInfoScreen(id: Long) {
        flightsListScreenRouter.openFlightInfoScreen(id)
    }

    fun openNewFlightScreen() {
        flightsListScreenRouter.openNewFlightScreen()
    }
}