package pt.course.airbooking.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "flights", foreignKeys = [ForeignKey(
        entity = PlaneTypeDbModel::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("planeTypeId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class FlightDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val departureLocation: String,
    val destinationLocation: String,
    val departureAirport: String,
    val departureAirportCode: String,
    val destinationAirport: String,
    val destinationAirportCode: String,
    val departureTimestamp: String,
    val arrivalTimestamp: String,
    @ColumnInfo(index = true)
    val planeTypeId: Long
)