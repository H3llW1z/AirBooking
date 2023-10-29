package pt.course.airbooking.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import pt.course.airbooking.AirBookingApplication
import pt.course.airbooking.di.annotation.ApplicationScope
import pt.course.airbooking.di.module.DataModule
import pt.course.airbooking.di.module.NavigationModule
import pt.course.airbooking.di.module.RoutersModule
import pt.course.airbooking.di.module.ScreensModule
import pt.course.airbooking.di.module.ViewModelModule

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        NavigationModule::class,
        ScreensModule::class,
        RoutersModule::class
    ]
)
@ApplicationScope
interface ApplicationComponent {

    fun inject(app: AirBookingApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}