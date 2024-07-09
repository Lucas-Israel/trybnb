package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentProfileBinding
import com.betrybe.trybnb.ui.viewmodels.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profileVM: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(view)

        binding.loginButtonProfile.setOnClickListener {
            val loginInput = binding.loginInputProfile
            val passInput = binding.passwordInputProfile

            isInputEmpty(loginInput)
            isInputEmpty(passInput)

            val loginText = loginInput.editText?.text.toString()
            val passText = passInput.editText?.text.toString()

            profileVM.login(loginText, passText)

            if (!profileVM.failure.value) Snackbar.make(
                binding.profileScrollView,
                getString(R.string.login_feito_com_sucesso),
                Snackbar.LENGTH_SHORT
            ).show()


        }

        return view
    }

    private fun isInputEmpty(input: TextInputLayout) {
        if (input.editText?.text?.isEmpty() == true) {
            when (input.id) {
                R.id.login_input_profile -> {
                    input.error = getString(R.string.email_empty_warning)
                }

                R.id.password_input_profile -> {
                    input.error = getString(R.string.password_empty_warning)
                }
            }
        } else {
            input.error = null
        }
    }


}