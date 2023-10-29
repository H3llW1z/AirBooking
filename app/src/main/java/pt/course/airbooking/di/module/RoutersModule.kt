package pt.course.airbooking.di.module

import dagger.Binds
import dagger.Module
import pt.course.airbooking.navigation.InitialScreenRouterImpl
import pt.course.airbooking.presentation.initial.InitialScreenRouter

@Module
interface RoutersModule {

    @Binds
    fun bindInitialScreenRouter(impl: InitialScreenRouterImpl): InitialScreenRouter
}