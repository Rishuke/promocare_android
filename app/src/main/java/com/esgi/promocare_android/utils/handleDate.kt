package com.esgi.promocare_android.utils

val monthInCalendar = listOf(
    "Janvier",
    "Février",
    "Mars",
    "Avril",
    "Mai",
    "Juin",
    "Juillet",
    "Août",
    "Septembre",
    "Octobre",
    "Novembre",
    "Décembre"
)

fun handleDate(date: String): String {
    val year = date.substring(0, 4)
    val month = monthInCalendar[date.substring(5, 7).toInt()-1]
    val day = date.substring(8, 10)

    return "$day $month $year"
}