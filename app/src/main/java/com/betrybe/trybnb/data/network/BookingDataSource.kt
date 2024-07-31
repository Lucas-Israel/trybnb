package com.betrybe.trybnb.data.network

import com.betrybe.trybnb.data.api.ApiService
import com.betrybe.trybnb.data.response.BookingId
import com.betrybe.trybnb.data.response.CreateBooking
import com.betrybe.trybnb.data.response.Reservation
import javax.inject.Inject

class BookingDataSource @Inject constructor(private val apiService: ApiService) {


    suspend fun getBookings(): List<BookingId> {
        val bookingsResponse = apiService.getBookings()

        return bookingsResponse
    }

    suspend fun getBookingById(id: String): Reservation {
        val bookResult = apiService.getBookingById(id)

        return bookResult
    }

    suspend fun createBooking(body: Reservation): CreateBooking {
        val createBookingResponse = apiService.createBooking(body)

        return createBookingResponse
    }
}
