package com.betrybe.trybnb.common.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatterUtils {

    fun reformatDate(inputDate: String): String? {
        val inputFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return try {
            val date = inputFormatter.parse(inputDate)
            if (date != null) {
                outputFormatter.format(date)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
