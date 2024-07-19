package com.betrybe.trybnb.data.models

data class Booking(
    val fullName: String,
    val price: String,
    val checkin: String,
    val checkout: String,
    val aditionalNeeds: String,
    val depositpaid: Boolean
)