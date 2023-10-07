package pt.course.airbooking.data

import pt.course.airbooking.data.db.model.FlightDbModel
import pt.course.airbooking.data.db.model.PlaneTypeDbModel
import pt.course.airbooking.domain.entity.Flight
import pt.course.airbooking.domain.entity.PlaneType

fun Flight.toDbModel() = FlightDbModel(
    departureAirport = departureAirport,
    departureLocation = departureLocation,
    destinationLocation = destinationLocation,
    departureAirportCode = departureAirportCode,
    destinationAirport = destinationAirport,
    destinationAirportCode = destinationAirportCode,
    departureTimestamp = departureTimestamp,
    arrivalTimestamp = arrivalTimestamp,
    planeTypeId = planeTypeId
)

fun FlightDbModel.toEntity() = Flight(
    id,
    departureLocation,
    destinationLocation,
    departureAirport,
    departureAirportCode,
    destinationAirport,
    destinationAirportCode,
    departureTimestamp,
    arrivalTimestamp,
    planeTypeId
)

fun PlaneTypeDbModel.toEntity() = PlaneType(id, description, capacity)
