package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentReservationBinding
import com.betrybe.trybnb.ui.adapters.BookingAdapter
import com.betrybe.trybnb.ui.viewmodels.BookingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationFragment: Fragment() {
    private lateinit var binding: FragmentReservationBinding
    private val viewModel: BookingViewModel by viewModels()
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_reservation, container, false)
        binding = FragmentReservationBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.reservation_recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getBookings()
        viewModel.bookings.observe(this) {
            mRecyclerView.adapter = BookingAdapter(it)
        }
    }

}