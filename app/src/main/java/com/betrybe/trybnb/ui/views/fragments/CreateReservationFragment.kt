package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.utils.BookingCreatorHelper.reservationBodyCreator
import com.betrybe.trybnb.common.utils.TextInputLayoutUtils.containerInputsValidationFlow
import com.betrybe.trybnb.databinding.FragmentCreateReservationBinding
import com.betrybe.trybnb.ui.viewmodels.BookingViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateReservationFragment : Fragment() {

    private val bookingVm: BookingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_create_reservation, container, false)
        val binding = FragmentCreateReservationBinding.bind(view)

        binding.createReservationButton.setOnClickListener {
            val areInputsValid = containerInputsValidationFlow(container!!)

            when (areInputsValid) {
                true -> {
                    val body = reservationBodyCreator(binding)

                    bookingVm.createBooking(body)

                    reservationCreationSuccessMessage(binding)
                }

                false -> return@setOnClickListener
            }
        }

        return binding.root
    }

    /**
     * Shows a Snackbar with a success message when a reservation is successfully created.
     */
    private fun reservationCreationSuccessMessage(bind: FragmentCreateReservationBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            bookingVm.isBookingCreationSuccess.collect { success ->
                success?.let {
                    Snackbar.make(
                        bind.createReservationScrollView,
                        getString(R.string.reservation_success),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}
