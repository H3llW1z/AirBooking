package pt.course.airbooking.domain.entity

data class PlaneType(
    val id: Long,
    val name: String,
    val description: String,
    val capacity: Int
)
