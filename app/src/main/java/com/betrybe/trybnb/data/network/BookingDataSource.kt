package com.betrybe.trybnb.data.network

import com.betrybe.trybnb.data.api.ApiServiceClient
import com.betrybe.trybnb.data.models.BookingId

class BookingDataSource {

    private val service = ApiServiceClient.instance

    suspend fun getBookings(): List<BookingId>? {
        val bookingsResponse = service.getBookings()

        return bookingsResponse.body()
    }
}