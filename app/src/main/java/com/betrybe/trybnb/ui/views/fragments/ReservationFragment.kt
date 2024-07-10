package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentReservationBinding
import com.betrybe.trybnb.ui.viewmodels.BookingViewModel


class ReservationFragment: Fragment() {
    private lateinit var binding: FragmentReservationBinding
    private val bookingVM: BookingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservation, container, false)
        binding = FragmentReservationBinding.bind(view)

        bookingVM.getBookings()

        return view
    }
}