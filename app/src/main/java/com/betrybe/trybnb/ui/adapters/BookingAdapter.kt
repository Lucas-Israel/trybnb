package com.betrybe.trybnb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.databinding.ListItemReservationBinding

class BookingAdapter(private val bookings: List<Booking>) :
    Adapter<BookingAdapter.BookingViewHolder>() {

    private lateinit var binding: ListItemReservationBinding


    class BookingViewHolder(private val binding: ListItemReservationBinding) :
        ViewHolder(binding.root) {
        fun bind(bookings: Booking) {
            binding.vm = bookings
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