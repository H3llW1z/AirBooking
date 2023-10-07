package pt.course.airbooking.domain

import pt.course.airbooking.domain.entity.BookingResult
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.entity.Passport
import pt.course.airbooking.domain.entity.PlaneType

interface BookingRepository {

    suspend fun getAllFlights(): List<Flight>

    suspend fun getFlightsByAirportCode(code: String): List<Flight>

    suspend fun addFlight(flight: Flight)

    suspend fun removeFlightById(id: Long)

    suspend fun getAllPlaneTypes(): List<PlaneType>

    suspend fun getPlaneTypeById(id: Long): PlaneType

    suspend fun bookTicket(passport: Passport): BookingResult
}