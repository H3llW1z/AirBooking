package pt.course.airbooking.presentation.formatter

import java.text.SimpleDateFormat
import java.util.Locale

fun getDateFormatter() = SimpleDateFormat("EE, dd MMM", Locale.getDefault())

fun getTimeFormatter() = SimpleDateFormat("HH:mm", Locale.getDefault())
