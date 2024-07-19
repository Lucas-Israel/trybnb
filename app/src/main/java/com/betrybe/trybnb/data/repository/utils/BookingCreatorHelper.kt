package com.betrybe.trybnb.data.repository.utils

import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.models.Booking

class BookingCreatorHelper {

    fun bookingCreatorFromBook(book: Book): Booking {
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