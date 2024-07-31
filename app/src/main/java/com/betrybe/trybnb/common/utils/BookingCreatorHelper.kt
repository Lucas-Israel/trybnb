package com.betrybe.trybnb.common.utils

import com.betrybe.trybnb.model.Booking
import com.betrybe.trybnb.data.response.Reservation
import com.betrybe.trybnb.databinding.FragmentCreateReservationBinding
import com.betrybe.trybnb.model.BookingDates

object BookingCreatorHelper {

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

    fun reservationBodyCreator(binding: FragmentCreateReservationBinding, bookingDates: BookingDates): Reservation {
        return Reservation(
            firstname = binding.firstNameCreateReservation.editText?.text.toString(),
            lastname = binding.lastNameCreateReservation.editText?.text.toString(),
            totalprice = binding.totalPriceCreateReservation.editText?.text.toString()
                .toInt(),
            depositpaid = binding.depositpaidCreateReservation.isChecked,
            bookingdates = bookingDates,
            additionalneeds = binding.additionalNeedsCreateReservation.editText?.text.toString()
        )
    }
}
