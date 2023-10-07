package pt.course.airbooking.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import pt.course.airbooking.di.module.DataModule

@Component(modules = [DataModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}