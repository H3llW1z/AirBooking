package pt.course.airbooking.presentation.planeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.course.airbooking.domain.entity.PlaneType
import pt.course.airbooking.domain.usecase.GetAllPlaneTypesUseCase
import pt.course.airbooking.presentation.base.State
import javax.inject.Inject

class PlanesListViewModel @Inject constructor(
    private val getAllPlaneTypesUseCase: GetAllPlaneTypesUseCase
) : ViewModel() {

    private val _state: MutableLiveData<State<List<PlaneType>>> = MutableLiveData(State.Initial)
    val state: LiveData<State<List<PlaneType>>> get() = _state

    fun loadPlanesList() {
        viewModelScope.launch {
            val result = getAllPlaneTypesUseCase()
            if (result.isEmpty()) {
                _state.value = State.EmptyResult
            } else {
                _state.value = State.Content(result)
            }
        }
    }
}