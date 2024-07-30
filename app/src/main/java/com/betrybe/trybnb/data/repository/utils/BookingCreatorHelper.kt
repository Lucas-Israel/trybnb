package com.betrybe.trybnb.data.repository.utils

import com.betrybe.trybnb.model.Booking
import com.betrybe.trybnb.model.Reservation

class BookingCreatorHelper {

    fun bookingCreatorFromBook(book: Reservation): Booking {
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