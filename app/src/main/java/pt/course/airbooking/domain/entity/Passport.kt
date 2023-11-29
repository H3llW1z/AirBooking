package pt.course.airbooking.domain.entity

data class Passport(
    val sex: Sex,
    val lastName: String,
    val name: String,
    val patronymic: String?,
    val dateOfBirth: String,
    val number: String,
)
