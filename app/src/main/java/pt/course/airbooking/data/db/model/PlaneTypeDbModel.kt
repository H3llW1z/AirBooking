package pt.course.airbooking.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plane_types")
data class PlaneTypeDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val description: String,
    val capacity: Int
)