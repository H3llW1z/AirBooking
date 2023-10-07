package pt.course.airbooking.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.course.airbooking.data.db.model.FlightDbModel

@Dao
interface FlightsDao {

    @Query("SELECT * FROM flights")
    suspend fun getAllFlights(): List<FlightDbModel>

    @Query("SELECT * FROM flights WHERE departureAirportCode = :code")
    suspend fun getFlightsByAirportCode(code: String): List<FlightDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlight(flight: FlightDbModel)

    @Query("DELETE FROM flights WHERE id = :id")
    suspend fun deleteFlightById(id: Long)

}