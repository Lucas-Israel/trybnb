package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.response.CreateBooking
import com.betrybe.trybnb.data.config.Configs.QUANTITY_OF_BOOKINGS_AT_A_TIME
import com.betrybe.trybnb.model.Booking
import com.betrybe.trybnb.data.utils.ClientResult
import com.betrybe.trybnb.data.network.BookingDataSource
import com.betrybe.trybnb.data.utils.BookingCreatorHelper
import com.betrybe.trybnb.data.response.Reservation
import com.betrybe.trybnb.data.utils.safeApiCall
import javax.inject.Inject

class BookingRepository @Inject constructor(private val bookingDS: BookingDataSource) {

    suspend fun getBookings(): ClientResult<List<Booking>> {
        return safeApiCall {
            bookingDS.getBookings().take(QUANTITY_OF_BOOKINGS_AT_A_TIME).map {
                val bookingById = bookingDS.getBookingById(it.bookingid.toString())
                BookingCreatorHelper().bookingCreatorFromReservation(bookingById)
            }
        }
    }

    suspend fun createBooking(booking: Reservation): ClientResult<CreateBooking> {
        return safeApiCall {
            bookingDS.createBooking(booking)
        }
    }
}
