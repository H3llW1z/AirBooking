package pt.course.airbooking.domain.entity

data class Flight(
    val id: Long,
    val departureLocation: String,
    val destinationLocation: String,
    val departureAirport: String,
    val departureAirportCode: String,
    val destinationAirport: String,
    val destinationAirportCode: String,
    val departureTimestamp: Long,
    val arrivalTimestamp: Long,
    val planeTypeId: Long
)
