package com.betrybe.trybnb.data.response

import com.betrybe.trybnb.model.BookingDates

data class Reservation(
    val firstname: String,
    val lastname: String,
    val totalprice: Int,
    val depositpaid: Boolean,
    val bookingdates: BookingDates,
    val additionalneeds: String
)