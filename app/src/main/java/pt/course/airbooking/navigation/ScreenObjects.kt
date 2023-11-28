package pt.course.airbooking.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import pt.course.airbooking.presentation.congratulations.CongratulationsFragment
import pt.course.airbooking.presentation.flightinformation.FlightInformationFragment
import pt.course.airbooking.presentation.flightinformationchange.FlightInformationChangeFragment
import pt.course.airbooking.presentation.flightslist.FlightsListFragment
import pt.course.airbooking.presentation.initial.InitialFragment
import pt.course.airbooking.presentation.personaldocument.PersonalDocumentFragment
import pt.course.airbooking.presentation.planeslist.PlanesListFragment

fun getInitialScreen(): FragmentScreen = FragmentScreen { InitialFragment.newInstance() }
fun getFlightsListScreen(): FragmentScreen = FragmentScreen { FlightsListFragment.newInstance() }
fun getPlanesListScreen(): FragmentScreen = FragmentScreen { PlanesListFragment.newInstance() }
fun getFlightInformationScreen(id: Long): FragmentScreen =
    FragmentScreen { FlightInformationFragment.newInstance(id) }

fun getFlightInformationChangeScreen(id: Long): FragmentScreen =
    FragmentScreen { FlightInformationChangeFragment.newInstance(id) }

fun getNewFlightScreen(): FragmentScreen =
    FragmentScreen { FlightInformationChangeFragment.newInstance() }

fun getPersonalDocumentScreen(): FragmentScreen =
    FragmentScreen { PersonalDocumentFragment.newInstance() }

fun getCongratulationsScreen(): FragmentScreen =
    FragmentScreen { CongratulationsFragment.newInstance() }
