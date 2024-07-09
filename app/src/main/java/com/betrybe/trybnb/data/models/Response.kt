package com.betrybe.trybnb.data.models

data class Response<out T> (
    val success: Boolean,
    val message: String,
    val data: T?
)