package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.models.BookingId
import com.betrybe.trybnb.data.models.Response
import com.betrybe.trybnb.data.network.BookingDataSource
import java.net.ConnectException

class BookingRepository(private val bookingDS: BookingDataSource = BookingDataSource()) {

    suspend fun getBookings(): Response<List<BookingId>> {
        val bookingsResponse = bookingDS.getBookings()
        val bookingList = mutableListOf<BookingId>()
        return try {
            for (i in 0..4){
                bookingList.add(bookingsResponse!![i])
            }
            Response(true, "", bookingList)
        } catch (e: ConnectException) {
            Response(false, e.message.toString(), null)
        }
    }
}