package com.betrybe.trybnb.data.network

import android.util.Log
import com.betrybe.trybnb.data.api.ApiService
import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.api.model.BookingId
import com.betrybe.trybnb.data.api.model.CreateBooking
import javax.inject.Inject

class BookingDataSource @Inject constructor(private val apiService: ApiService) {


    suspend fun getBookings(): List<BookingId>? {
        val bookingsResponse = apiService.getBookings()

        return bookingsResponse.body()
    }

    suspend fun getBookingById(id: String): Book? {
        val bookResult = apiService.getBookingById(id)

        return bookResult.body()
    }

    suspend fun createBooking(body: Book): CreateBooking? {
        val createBookingResponse = apiService.createBooking(body)

        return createBookingResponse.body()
    }
}