package com.betrybe.trybnb.data.utils

import com.betrybe.trybnb.model.Booking
import com.betrybe.trybnb.data.response.Reservation

class BookingCreatorHelper {

    fun bookingCreatorFromReservation(reservation: Reservation): Booking {
        return Booking(
            reservation.firstname + " " + reservation.lastname,
            reservation.totalprice.toString(),
            reservation.bookingdates.checkin,
            reservation.bookingdates.checkout,
            reservation.additionalneeds,
            reservation.depositpaid
        )
    }
}
