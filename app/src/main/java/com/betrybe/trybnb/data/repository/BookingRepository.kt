package com.betrybe.trybnb.data.repository


import com.betrybe.trybnb.BuildConfig.QUANTITY_OF_BOOKINGS_AT_A_TIME
import com.betrybe.trybnb.data.response.CreateBooking
import com.betrybe.trybnb.model.Booking
import com.betrybe.trybnb.common.utils.ClientResult
import com.betrybe.trybnb.data.network.BookingDataSource
import com.betrybe.trybnb.common.utils.BookingCreatorHelper
import com.betrybe.trybnb.data.response.Reservation
import com.betrybe.trybnb.common.utils.safeApiCall
import javax.inject.Inject

class BookingRepository @Inject constructor(private val bookingDS: BookingDataSource) {

    /**
     * Retrieves a list of bookings from the API.
     * @return A list of [Booking] objects representing the bookings.
     */
    suspend fun getBookings(): ClientResult<List<Booking>> {
        return safeApiCall {
            bookingDS.getBookings().take(QUANTITY_OF_BOOKINGS_AT_A_TIME).map {
                val bookingById = bookingDS.getBookingById(it.bookingid.toString())
                BookingCreatorHelper.bookingCreatorFromReservation(bookingById)
            }
        }
    }

    /**
     * Creates a new [Reservation] in the API.
     * @param reservation The [Reservation] object containing the reservation details.
     * @return A [CreateBooking] representing the result of the API call.
     */
    suspend fun createBooking(reservation: Reservation): ClientResult<CreateBooking> {
        return safeApiCall {
            bookingDS.createBooking(reservation)
        }
    }
}
