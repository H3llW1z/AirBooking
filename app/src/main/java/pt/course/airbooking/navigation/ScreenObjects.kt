package pt.course.airbooking.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import pt.course.airbooking.presentation.flightslist.FlightsListFragment
import pt.course.airbooking.presentation.initial.InitialFragment

fun getInitialScreen(): FragmentScreen = FragmentScreen { InitialFragment.newInstance() }
fun getFlightsListScreen(): FragmentScreen = FragmentScreen { FlightsListFragment.newInstance() }
