package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.api.model.CreateBooking
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.Response
import com.betrybe.trybnb.data.network.BookingDataSource
import java.net.ConnectException
import javax.inject.Inject

class BookingRepository @Inject constructor(private val bookingDS: BookingDataSource) {

    suspend fun getBookings(): Response<List<Booking>> {
        val bookingsResponse = bookingDS.getBookings()?.take(5)
        val bookingList = mutableListOf<Booking>()
        try {
            bookingsResponse?.forEach {
                val bookingById = bookingDS.getBookingById(it.bookingid.toString())
                if (bookingById != null) {
                    val booking = bookingCreatorFromBook(bookingById)
                    bookingList.add(booking)
                }
            }
            return Response(true, "", bookingList)
        } catch (e: ConnectException) {
            return Response(false, e.message.toString(), null)
        }
    }

    suspend fun createBooking(booking: Book): Response<CreateBooking> {
        try {
            val bookingResponse = bookingDS.createBooking(booking)
            return Response(true, "", bookingResponse)
        } catch (e: ConnectException) {
            return Response(false, e.message.toString(), null)
        }
    }

    private fun bookingCreatorFromBook(book: Book): Booking {
        return Booking(
            book.firstname + " " + book.lastname,
            book.totalprice.toString(),
            book.bookingdates.checkin,
            book.bookingdates.checkout,
            book.additionalneeds,
            book.depositpaid
        )
    }
}
