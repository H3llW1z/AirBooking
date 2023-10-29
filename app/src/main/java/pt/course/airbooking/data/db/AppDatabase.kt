package pt.course.airbooking.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.course.airbooking.data.db.dao.FlightsDao
import pt.course.airbooking.data.db.dao.PlaneTypesDao
import pt.course.airbooking.data.db.model.FlightDbModel
import pt.course.airbooking.data.db.model.PlaneTypeDbModel

@Database(
    entities = [FlightDbModel::class, PlaneTypeDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun flightsDao(): FlightsDao

    abstract fun planeTypesDao(): PlaneTypesDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "main.db"

        fun getInstance(application: Application): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
    }
}