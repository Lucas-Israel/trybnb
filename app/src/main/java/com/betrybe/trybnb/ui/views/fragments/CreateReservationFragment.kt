package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentCreateReservationBinding
import com.google.android.material.textfield.TextInputLayout

class CreateReservationFragment : Fragment() {
    private lateinit var binding: FragmentCreateReservationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_create_reservation, container, false)
        binding = FragmentCreateReservationBinding.bind(view)

        binding.createReservationButton.setOnClickListener {
            val reservationLinearLayout: ViewGroup = view.findViewById(R.id.reservation_linear_layout)
            // Looping through all child views in the LinearLayout
            for (i in 0 until reservationLinearLayout.childCount) {
                val child = reservationLinearLayout.getChildAt(i)
                // Only TextInputLayout children go through
                if (child !is TextInputLayout) continue
                isInputEmpty(child)
            }

        }

        return binding.root
    }

    private fun isInputEmpty(input: TextInputLayout) {
            if (input.editText?.text?.isEmpty() == true) {
                input.error = "O campo ${input.hint.toString().replace(" do Hóspede", "")} é obrigatório"
            } else {
                input.error = null
            }
    }

}