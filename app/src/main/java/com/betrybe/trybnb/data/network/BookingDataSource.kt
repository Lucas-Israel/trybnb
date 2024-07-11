package com.betrybe.trybnb.data.network

import android.util.Log
import com.betrybe.trybnb.data.api.ApiServiceClient
import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.api.model.BookingId

class BookingDataSource {

    private val service = ApiServiceClient.instance

    suspend fun getBookings(): List<BookingId>? {
        val bookingsResponse = service.getBookings()

        return bookingsResponse.body()
    }

    suspend fun getBookingById(id: String): Book? {
        val bookResult = service.getBookingById(id)

        return bookResult.body()
    }
}