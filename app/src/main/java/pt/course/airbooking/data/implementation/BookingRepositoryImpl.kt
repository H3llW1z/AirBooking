package pt.course.airbooking.data.implementation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.course.airbooking.data.db.dao.FlightsDao
import pt.course.airbooking.data.db.dao.PlaneTypesDao
import pt.course.airbooking.data.toDbModel
import pt.course.airbooking.data.toEntity
import pt.course.airbooking.domain.BookingRepository
import pt.course.airbooking.domain.entity.BookingResult
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.entity.Passport
import pt.course.airbooking.domain.entity.PlaneType
import javax.inject.Inject
import kotlin.random.Random

class BookingRepositoryImpl @Inject constructor(
    private val flightsDao: FlightsDao,
    private val planeTypesDao: PlaneTypesDao
) : BookingRepository {

    override suspend fun getAllFlights(): List<Flight> = withContext(Dispatchers.IO) {
        flightsDao.getAllFlights().map { it.toEntity() }
    }

    override suspend fun getFlightsByAirportCode(code: String): List<Flight> =
        withContext(Dispatchers.IO) {
            flightsDao.getFlightsByAirportCode(code).map { it.toEntity() }
        }

    override suspend fun addFlight(flight: Flight) = withContext(Dispatchers.IO) {
        flightsDao.insertFlight(flight.toDbModel())
    }

    override suspend fun removeFlightById(id: Long) = withContext(Dispatchers.IO) {
        flightsDao.deleteFlightById(id)
    }

    override suspend fun getAllPlaneTypes(): List<PlaneType> = withContext(Dispatchers.IO) {
        val planeTypes = planeTypesDao.getAllPlaneTypes().map { it.toEntity() }
        return@withContext planeTypes.ifEmpty {
            val planeTypesFromDataSource = PlaneTypeDataSource.getPlaneTypes()
            planeTypesDao.insertPlaneTypes(planeTypesFromDataSource)
            planeTypesFromDataSource.map { it.toEntity() }
        }
    }

    override suspend fun bookTicket(passport: Passport, flightId: Long): BookingResult =
        withContext(Dispatchers.IO) {
            with(passport) {
                return@withContext if (lastName.isNotEmpty() && name.isNotEmpty() && dateOfBirth.isNotEmpty()
                    && citizenship.isNotEmpty() && number.isNotEmpty() && countryOfIssue.isNotEmpty()
                    && validityPeriod.isNotEmpty()
                ) {
                    BookingResult.Success(Random.nextLong().toString())
                } else {
                    BookingResult.InvalidInput
                }
            }

        }

    override suspend fun getPlaneTypeById(id: Long): PlaneType? = withContext(Dispatchers.IO) {
        planeTypesDao.getPlaneTypeById(id)?.toEntity()
    }

    override suspend fun getFlightById(id: Long): Flight? = withContext(Dispatchers.IO) {
        flightsDao.getFlightById(id)?.toEntity()
    }
}