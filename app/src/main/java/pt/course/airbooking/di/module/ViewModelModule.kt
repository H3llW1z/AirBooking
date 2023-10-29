package pt.course.airbooking.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pt.course.airbooking.di.annotation.ViewModelKey
import pt.course.airbooking.presentation.flightslist.FlightsListViewModel
import pt.course.airbooking.presentation.initial.InitialViewModel

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
}