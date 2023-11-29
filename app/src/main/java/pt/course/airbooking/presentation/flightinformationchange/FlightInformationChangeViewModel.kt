package pt.course.airbooking.presentation.flightinformationchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.entity.PlaneType
import pt.course.airbooking.domain.usecase.AddFlightUseCase
import pt.course.airbooking.domain.usecase.GetAllPlaneTypesUseCase
import pt.course.airbooking.domain.usecase.GetFlightByIdUseCase
import pt.course.airbooking.presentation.base.State
import javax.inject.Inject

class FlightInformationChangeViewModel @Inject constructor(
    private val getFlightByIdUseCase: GetFlightByIdUseCase,
    private val addFlightUseCase: AddFlightUseCase,
    private val getAllPlaneTypesUseCase: GetAllPlaneTypesUseCase,
    private val flightInformationChangeRouter: FlightInformationChangeRouter
) : ViewModel() {

    private val _state: MutableLiveData<State<Pair<Flight?, List<PlaneType>>>> =
        MutableLiveData(State.Initial)

    val state: LiveData<State<Pair<Flight?, List<PlaneType>>>>
        get() = _state

    fun getFlightAndPlanesInfo(flightId: Long) {
        viewModelScope.launch {
            val flight = getFlightByIdUseCase(flightId)
            if (flight == null) {
                flightInformationChangeRouter.goBack()
                return@launch
            }
            val planeTypes = getAllPlaneTypesUseCase()
            _state.value = State.Content(flight to planeTypes)
        }
    }

    fun getPlanesInfo() {
        viewModelScope.launch {
            val planeTypes = getAllPlaneTypesUseCase()
            _state.value = State.Content(null to planeTypes)
        }
    }

    fun saveFlight(flight: Flight) {
        viewModelScope.launch {
            addFlightUseCase(flight)
            flightInformationChangeRouter.goBack()
        }
    }
}