package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.api.model.CreateBooking
import com.betrybe.trybnb.data.config.Configs.QUANTITY_OF_BOOKINGS_AT_A_TIME
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.ClientResult
import com.betrybe.trybnb.data.network.BookingDataSource
import com.betrybe.trybnb.data.repository.utils.BookingCreatorHelper
import com.betrybe.trybnb.data.repository.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookingRepository @Inject constructor(private val bookingDS: BookingDataSource) {

    suspend fun getBookings(): ClientResult<List<Booking>> {
        return withContext(DispatcherProvider.IO) {

            val bookingsResponse =
                bookingDS.getBookings()?.take(QUANTITY_OF_BOOKINGS_AT_A_TIME)
                    ?: return@withContext ClientResult.ClientError(
                        isServerError = false,
                        isNetworkError = true
                    )

            val bookingList = mutableListOf<Booking>()

            bookingsResponse.forEach {
                val bookingById = bookingDS.getBookingById(it.bookingid.toString())
                if (bookingById != null) {
                    val booking = BookingCreatorHelper().bookingCreatorFromBook(bookingById)
                    bookingList.add(booking)
                }
            }
            return@withContext ClientResult.ClientSuccess(bookingList)
        }
    }

    suspend fun createBooking(booking: Book): ClientResult<CreateBooking> {
        return withContext(DispatcherProvider.IO) {
            val bookingResponse = bookingDS.createBooking(booking)
                ?: return@withContext ClientResult.ClientError(
                    isServerError = false,
                    isNetworkError = true
                )
            return@withContext ClientResult.ClientSuccess(bookingResponse)
        }
    }
}

