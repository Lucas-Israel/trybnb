package com.betrybe.trybnb.common.utils

import android.content.Context
import com.betrybe.trybnb.R
import com.betrybe.trybnb.model.Booking
import com.betrybe.trybnb.data.response.Reservation
import com.betrybe.trybnb.databinding.FragmentCreateReservationBinding
import com.betrybe.trybnb.model.BookingDates

/**
 * This object provides helper functions for creating Booking and Reservation objects.
 */
object BookingCreatorHelper {

    /**
     * Creates a Booking object from a Reservation object.
     * @param reservation The Reservation object to be converted.
     * @return A Booking object.
     */
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

    /**
     * Creates a Reservation object from a FragmentCreateReservationBinding object.
     * @param binding The FragmentCreateReservationBinding object to be converted.
     * @return A Reservation object.
     */
    fun reservationBodyCreator(
        binding: FragmentCreateReservationBinding,
    ): Reservation {
        return Reservation(
            firstname = binding.firstNameCreateReservation.editText?.text.toString(),
            lastname = binding.lastNameCreateReservation.editText?.text.toString(),
            totalprice = binding.totalPriceCreateReservation.editText?.text.toString()
                .toInt(),
            depositpaid = binding.depositpaidCreateReservation.isChecked,
            bookingdates = bookingDatesCreator(
                binding.checkinCreateReservation.editText?.text.toString(),
                binding.checkoutCreateReservation.editText?.text.toString(),
                binding.root.context
            ),
            additionalneeds = binding.additionalNeedsCreateReservation.editText?.text.toString()
        )
    }

    /**
     * Creates a BookingDates object from check-in and check-out dates, used as an aid for Reservation creation.
     * @param checkIn The check-in date as a string.
     * @param checkOut The check-out date as a string.
     * @param context The application context.
     * @return A BookingDates object.
     */
    private fun bookingDatesCreator(
        checkIn: String,
        checkOut: String,
        context: Context
    ): BookingDates {
        val formattedCheckIn = DateFormatterUtils.reformatDate(checkIn)
        val formattedCheckOut = DateFormatterUtils.reformatDate(checkOut)
        require(formattedCheckIn != null && formattedCheckOut != null) {
            UiText.StringResource(R.string.checkIn_checkOut_needs_proper_format).asString(context)
        }
        return BookingDates(formattedCheckIn, formattedCheckOut)
    }
}
