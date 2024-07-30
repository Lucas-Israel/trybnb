package com.betrybe.trybnb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.betrybe.trybnb.R
import com.betrybe.trybnb.model.Booking
import com.betrybe.trybnb.databinding.ListItemReservationBinding

class BookingAdapter(private val bookings: List<Booking>) :
    Adapter<BookingAdapter.BookingViewHolder>() {

    private lateinit var binding: ListItemReservationBinding

    class BookingViewHolder(private val binding: ListItemReservationBinding) :
        ViewHolder(binding.root) {
        fun bind(bookings: Booking) {
            binding.nameItemReservation.text = bookings.fullName
            binding.checkinItemReservation.text = bookings.checkin
            binding.checkoutItemReservation.text = bookings.checkout
            binding.additionalNeedsItemReservation.text = bookings.aditionalNeeds
            binding.totalPriceItemReservation.text = bookings.price
            if (bookings.depositpaid) {
                binding.depositpaidItemReservation.setImageResource(R.drawable.ic_depositpaid_true)
            } else {
                binding.depositpaidItemReservation.setImageResource(R.drawable.ic_depositpaid_false)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BookingViewHolder {
        binding =
            ListItemReservationBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return BookingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(bookings[position])
    }

    override fun getItemCount(): Int = bookings.size
}