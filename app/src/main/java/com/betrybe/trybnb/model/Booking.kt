package com.betrybe.trybnb.model

data class Booking(
    val fullName: String,
    val price: String,
    val checkin: String,
    val checkout: String,
    val aditionalNeeds: String,
    val depositpaid: Boolean
)
