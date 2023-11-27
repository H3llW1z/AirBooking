package pt.course.airbooking.presentation.flightslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.usecase.GetAllFlightsUseCase
import pt.course.airbooking.domain.usecase.GetFlightsByAirportCodeUseCase
import pt.course.airbooking.presentation.base.State
import javax.inject.Inject

class FlightsListViewModel @Inject constructor(
    private val getAllFlightsUseCase: GetAllFlightsUseCase,
    private val getFlightsByAirportCodeUseCase: GetFlightsByAirportCodeUseCase,
    private val flightsListScreenRouter: FlightsListScreenRouter
) : ViewModel() {

    private val _state: MutableLiveData<State<List<Flight>>> = MutableLiveData(State.Initial)
    val state: LiveData<State<List<Flight>>>
        get() = _state

    fun getAllFlights() {
//        viewModelScope.launch {
//            val result = getAllFlightsUseCase()
//            _state.value = if (result.isEmpty()) {
//                State.EmptyResult
//            } else {
//                State.Content(result)
//            }
//        }
        val flights = mutableListOf<Flight>()
        repeat(5) {
            val flight = Flight(it.toLong(), "Санкт-Петербург", "Алматы", "DepAP", "LED", "DestAp", "ALA", System.currentTimeMillis(), System.currentTimeMillis(), 1)
            flights.add(flight)
        }
        _state.value = State.Content(flights)
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

    fun openFlightInfoScreen(id: Long) {
        flightsListScreenRouter.openFlightInfoScreen(id)
    }
}