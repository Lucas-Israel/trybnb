package com.betrybe.trybnb.data.network

import com.betrybe.trybnb.data.api.ApiService
import com.betrybe.trybnb.data.response.BookingId
import com.betrybe.trybnb.data.response.CreateBooking
import com.betrybe.trybnb.data.response.Reservation
import javax.inject.Inject

class BookingDataSource @Inject constructor(private val apiService: ApiService) {

    /**
     * Retrieves a list of bookings from the API.
     * @return A list of [BookingId] objects representing the bookings.
     */
    suspend fun getBookings(): List<BookingId> {
        val bookingsResponse = apiService.getBookings()

        return bookingsResponse
    }

    /**
     * Retrieves a specific booking by its ID from the API.
     * @param id The ID of the booking to retrieve.
     * @return A [Reservation] object representing the booking.
     */
    suspend fun getBookingById(id: String): Reservation {
        val bookResult = apiService.getBookingById(id)

        return bookResult
    }

    /**
     * Creates a new booking using the provided [Reservation] object.
     * @param body The [Reservation] object containing the booking details.
     * @return A [CreateBooking] object representing the created booking.
     */
    suspend fun createBooking(body: Reservation): CreateBooking {
        val createBookingResponse = apiService.createBooking(body)

        return createBookingResponse
    }
}
