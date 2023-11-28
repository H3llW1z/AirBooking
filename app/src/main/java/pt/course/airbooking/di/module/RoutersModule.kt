package pt.course.airbooking.di.module

import dagger.Binds
import dagger.Module
import pt.course.airbooking.navigation.FlightInformationChangeRouterImpl
import pt.course.airbooking.navigation.FlightInformationScreenRouterImpl
import pt.course.airbooking.navigation.FlightsListScreenRouterImpl
import pt.course.airbooking.navigation.InitialScreenRouterImpl
import pt.course.airbooking.presentation.flightinformation.FlightInformationScreenRouter
import pt.course.airbooking.presentation.flightinformationchange.FlightInformationChangeRouter
import pt.course.airbooking.presentation.flightslist.FlightsListScreenRouter
import pt.course.airbooking.presentation.initial.InitialScreenRouter

@Module
interface RoutersModule {

    @Binds
    fun bindInitialScreenRouter(impl: InitialScreenRouterImpl): InitialScreenRouter

    @Binds
    fun bindFlightsListScreenRouter(impl: FlightsListScreenRouterImpl): FlightsListScreenRouter

    @Binds
    fun bindFlightInformationScreenRouter(impl: FlightInformationScreenRouterImpl): FlightInformationScreenRouter

    @Binds
    fun bindFlightInformationChangeRouter(impl: FlightInformationChangeRouterImpl): FlightInformationChangeRouter
}