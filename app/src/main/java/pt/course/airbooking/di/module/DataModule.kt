package pt.course.airbooking.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import pt.course.airbooking.data.db.AppDatabase
import pt.course.airbooking.data.db.dao.FlightsDao
import pt.course.airbooking.data.db.dao.PlaneTypesDao
import pt.course.airbooking.data.implementation.BookingRepositoryImpl
import pt.course.airbooking.di.annotation.ApplicationScope
import pt.course.airbooking.domain.BookingRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindBookingRepository(impl: BookingRepositoryImpl): BookingRepository

    companion object {

        @Provides
        fun provideFlightsDao(application: Application): FlightsDao =
            AppDatabase.getInstance(application).flightsDao()

        @Provides
        fun providePlaneTypesDao(application: Application): PlaneTypesDao =
            AppDatabase.getInstance(application).planeTypesDao()
    }
}