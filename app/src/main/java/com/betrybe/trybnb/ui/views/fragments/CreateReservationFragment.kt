package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentCreateReservationBinding
import com.google.android.material.textfield.TextInputLayout
import kotlin.reflect.typeOf

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
            areInputsEmpty(reservationLinearLayout)
        }

        return binding.root
    }

    private fun areInputsEmpty(viewG: ViewGroup) {
        for (i in 0 until viewG.childCount) {
            val child = viewG.getChildAt(i)
            if (child !is TextInputLayout) continue
            if (child.editText?.text?.isEmpty() == true) {

                child.error = "O campo ${child.hint.toString().replace(" do Hóspede", "")} é obrigatório"
            } else {
                child.error = null
            }
        }
    }

}