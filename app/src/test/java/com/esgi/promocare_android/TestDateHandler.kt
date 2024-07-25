package com.esgi.promocare_android

import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.handleDateShort
import org.junit.Test
import org.junit.Assert.*


class TestDateHandler {
    @Test
    fun date_to_date_long_format() {
        val testCases = mapOf(
            "2024-01-01T17:24:12.000Z" to "01 Janvier 2024",
            "2024-05-01T17:24:12.000Z" to "01 Mai 2024",
            "2024-08-15T17:24:12.000Z" to "15 Août 2024",
            "2024-09-10T17:24:12.000Z" to "10 Septembre 2024",
        )

        for ((input, expected) in testCases) {
            val result = handleDate(input)
            assertEquals(expected, result)
        }
    }

    @Test
    fun date_to_date_message_format(){
        val testCases = mapOf(
            "2024-01-01T17:24:12.000Z" to "01/01/2024",
            "2024-05-01T17:24:12.000Z" to "01/05/2024",
            "2024-08-15T17:24:12.000Z" to "15/08/2024",
            "2024-09-10T17:24:12.000Z" to "10/09/2024",
        )

        for ((input, expected) in testCases) {
            val result = handleDateShort(input)
            assertEquals(expected, result)
        }
    }
}