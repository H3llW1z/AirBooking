package pt.course.airbooking.domain.entity

data class Passport(
    val sex: Sex,
    val lastName: String,
    val name: String,
    val patronymic: String?,
    val dateOfBirth: String,
    val citizenship: String,
    val series: String?,
    val number: String,
    val countryOfIssue: String,
    val validityPeriod: String
)
