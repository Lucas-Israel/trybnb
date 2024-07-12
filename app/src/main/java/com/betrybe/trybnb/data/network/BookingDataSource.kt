package com.betrybe.trybnb.data.network

import android.util.Log
import com.betrybe.trybnb.data.api.ApiServiceClient
import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.api.model.BookingId
import com.betrybe.trybnb.data.api.model.CreateBooking

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

    suspend fun createBooking(body: Book): CreateBooking? {
        val createBookingResponse = service.createBooking(body)

        return createBookingResponse.body()
    }
}