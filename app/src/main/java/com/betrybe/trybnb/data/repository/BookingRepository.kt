package com.betrybe.trybnb.data.repository

import android.util.Log
import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.api.model.BookingId
import com.betrybe.trybnb.data.api.model.CreateBooking
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.Response
import com.betrybe.trybnb.data.network.BookingDataSource
import java.net.ConnectException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BookingRepository(private val bookingDS: BookingDataSource = BookingDataSource()) {

    suspend fun getBookings(): Response<List<Booking>> {
        val bookingsResponse = bookingDS.getBookings()?.take(5)
        val bookingList = mutableListOf<Booking>()
        try {
            bookingsResponse?.forEach {
                val bookingById = bookingDS.getBookingById(it.bookingid.toString())
                if (bookingById != null) {
                    val booking = Booking(
                        bookingById.firstname + " " + bookingById.lastname,
                        bookingById.totalprice.toString(),
                        bookingById.bookingdates.checkin,
                        bookingById.bookingdates.checkout,
                        bookingById.additionalneeds,
                        bookingById.depositpaid
                    )
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
}