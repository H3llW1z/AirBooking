package pt.course.airbooking.presentation.flightinformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.entity.PlaneType
import pt.course.airbooking.domain.usecase.GetFlightByIdUseCase
import pt.course.airbooking.domain.usecase.GetPlaneTypeByIdUseCase
import pt.course.airbooking.presentation.base.State
import javax.inject.Inject

class FlightInformationViewModel @Inject constructor(
    private val getFlightByIdUseCase: GetFlightByIdUseCase,
    private val getPlaneTypeByIdUseCase: GetPlaneTypeByIdUseCase,
    private val router: FlightInformationScreenRouter
) : ViewModel() {

    private val _state: MutableLiveData<State<Pair<Flight, PlaneType>>> =
        MutableLiveData(State.Initial)

    val state: LiveData<State<Pair<Flight, PlaneType>>>
        get() = _state

    fun loadFlightInfo(flightId: Long) {
        viewModelScope.launch {

            val flight = getFlightByIdUseCase(flightId)
            if (flight == null) {
                _state.value = State.EmptyResult
                router.goBack()
                return@launch
            }

            val planeType = getPlaneTypeByIdUseCase(flight.planeTypeId)
            if (planeType == null) {
                _state.value = State.EmptyResult
                router.goBack()
                return@launch
            }
            _state.value = State.Content(flight to planeType)
        }
    }

    fun openFlightEditScreen(flightId: Long) {
        router.openFlightEditScreen(flightId)
    }

    fun openBookingScreen(flightId: Long) {
        router.openBookingScreen(flightId)
    }
}