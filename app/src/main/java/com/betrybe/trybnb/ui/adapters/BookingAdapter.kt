package com.betrybe.trybnb.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.Response
import com.betrybe.trybnb.databinding.FragmentReservationBinding
import com.betrybe.trybnb.databinding.ListItemReservationBinding
import com.betrybe.trybnb.ui.viewmodels.BookingViewModel

// receber uma viewModel para fazer o bind dinamico
class BookingAdapter(private val viewModel: BookingViewModel, private val bookings: List<Booking>) :
    Adapter<BookingAdapter.BookingViewHolder>() {

//    class BookingViewHolder(itemView: View) :
//        ViewHolder(itemView) {
//        val fullName: TextView = itemView.findViewById(R.id.name_item_reservation)
//        val checkin: TextView = itemView.findViewById(R.id.checkin_item_reservation)
//        val checkout: TextView = itemView.findViewById(R.id.checkout_item_reservation)
//        val depositPaid: ImageView = itemView.findViewById(R.id.depositpaid_item_reservation)
//        val aditionalNeeds: TextView = itemView.findViewById(R.id.additional_needs_item_reservation)
//        val totalPrice: TextView = itemView.findViewById(R.id.total_price_item_reservation)
//    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_item_reservation, parent, false)
//        return BookingViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
//        holder.fullName.text = bookings[position].fullName
//        holder.checkin.text = bookings[position].checkin
//        holder.checkout.text = bookings[position].checkout
//        holder.aditionalNeeds.text = bookings[position].aditionalNeeds
//        holder.totalPrice.text = bookings[position].price
//
//        if (bookings[position].depositpaid) {
//            holder.depositPaid.setImageResource(R.drawable.ic_depositpaid_true)
//        } else {
//            holder.depositPaid.setImageResource(R.drawable.ic_depositpaid_false)
//        }
//    }

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
        viewModel.bookings.observeForever {
            if (it.isNotEmpty()) {
                holder.bind(it[position])
            }
        }
    }

    override fun getItemCount(): Int = bookings.size
}