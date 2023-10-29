package pt.course.airbooking.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.course.airbooking.presentation.MainActivity
import pt.course.airbooking.presentation.flightslist.FlightsListFragment
import pt.course.airbooking.presentation.initial.InitialFragment

@Module
interface ScreensModule {

    @ContributesAndroidInjector
    fun contributesMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    fun contributesInitialFragmentInjector(): InitialFragment

    @ContributesAndroidInjector
    fun contributesFlightsListFragmentInjector(): FlightsListFragment

}