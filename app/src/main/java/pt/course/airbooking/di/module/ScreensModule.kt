package pt.course.airbooking.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.course.airbooking.presentation.MainActivity
import pt.course.airbooking.presentation.congratulations.CongratulationsFragment
import pt.course.airbooking.presentation.flightinformation.FlightInformationFragment
import pt.course.airbooking.presentation.flightinformationchange.FlightInformationChangeFragment
import pt.course.airbooking.presentation.flightslist.FlightsListFragment
import pt.course.airbooking.presentation.initial.InitialFragment
import pt.course.airbooking.presentation.personaldocument.PersonalDocumentFragment
import pt.course.airbooking.presentation.planeslist.PlanesListFragment

@Module
interface ScreensModule {

    @ContributesAndroidInjector
    fun contributesMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    fun contributesInitialFragmentInjector(): InitialFragment

    @ContributesAndroidInjector
    fun contributesFlightsListFragmentInjector(): FlightsListFragment

    @ContributesAndroidInjector
    fun contributesFlightInformationFragmentInjector(): FlightInformationFragment

    @ContributesAndroidInjector
    fun contributesFlightInformationChangeFragmentInjector(): FlightInformationChangeFragment

    @ContributesAndroidInjector
    fun contributesCongratulationsFragmentInjector(): CongratulationsFragment

    @ContributesAndroidInjector
    fun contributesPersonalDocumentFragmentInjector(): PersonalDocumentFragment

    @ContributesAndroidInjector
    fun contributesPlanesListFragmentInjector(): PlanesListFragment

}