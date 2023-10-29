package pt.course.airbooking

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import pt.course.airbooking.di.DaggerApplicationComponent
import javax.inject.Inject

class AirBookingApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.factory().create(this).inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> =
        dispatchingAndroidInjector
}