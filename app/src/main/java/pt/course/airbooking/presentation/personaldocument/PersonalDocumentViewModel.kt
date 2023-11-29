package pt.course.airbooking.presentation.personaldocument

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.course.airbooking.domain.entity.BookingResult
import pt.course.airbooking.domain.entity.Passport
import pt.course.airbooking.domain.usecase.BookTicketUseCase
import pt.course.airbooking.presentation.base.State
import javax.inject.Inject

class PersonalDocumentViewModel @Inject constructor(
    private val bookTicketUseCase: BookTicketUseCase,
    private val personalDocumentScreenRouter: PersonalDocumentScreenRouter
) : ViewModel() {

    private val _state: MutableLiveData<State<BookingResult>> = MutableLiveData(State.Initial)
    val state: LiveData<State<BookingResult>>
        get() = _state

    fun bookTicket(passport: Passport, flightId: Long) {
        viewModelScope.launch {

            when (val result = bookTicketUseCase(passport, flightId)) {
                is BookingResult.Success -> {
                    personalDocumentScreenRouter.openCongratulationsScreen(result.bookingCode)
                }

                is BookingResult.InvalidInput -> {
                    _state.value = State.Content(result)
                }
            }
        }
    }
}