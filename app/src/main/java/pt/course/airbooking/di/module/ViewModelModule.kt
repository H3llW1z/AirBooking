package pt.course.airbooking.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pt.course.airbooking.di.annotation.ViewModelKey
import pt.course.airbooking.presentation.congratulations.CongratulationsViewModel
import pt.course.airbooking.presentation.flightinformation.FlightInformationViewModel
import pt.course.airbooking.presentation.flightinformationchange.FlightInformationChangeViewModel
import pt.course.airbooking.presentation.flightslist.FlightsListViewModel
import pt.course.airbooking.presentation.initial.InitialViewModel
import pt.course.airbooking.presentation.personaldocument.PersonalDocumentViewModel
import pt.course.airbooking.presentation.planeslist.PlanesListViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(InitialViewModel::class)
    fun bindInitialViewModel(viewModel: InitialViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FlightsListViewModel::class)
    fun bindFlightsListViewModel(viewModel: FlightsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlanesListViewModel::class)
    fun bindPlanesListViewModel(viewModel: PlanesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CongratulationsViewModel::class)
    fun bindCongratulationsViewModel(viewModel: CongratulationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FlightInformationViewModel::class)
    fun bindFlightInformationViewModel(viewModel: FlightInformationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FlightInformationChangeViewModel::class)
    fun bindFlightInformationChangeViewModel(viewModel: FlightInformationChangeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonalDocumentViewModel::class)
    fun bindPersonalDocumentViewModel(viewModel: PersonalDocumentViewModel): ViewModel
}