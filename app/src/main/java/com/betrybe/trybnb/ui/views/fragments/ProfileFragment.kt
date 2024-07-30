package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentProfileBinding
import com.betrybe.trybnb.ui.viewmodels.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
            val emailInput = binding.loginInputProfile
            val passInput = binding.passwordInputProfile
            val email = emailInput.editText?.text.toString()
            val password = passInput.editText?.text.toString()

            validatingInput(emailInput)
            validatingInput(passInput)

            if (email.isEmpty() || password.isEmpty()) return@setOnClickListener

            profileVM.login(email, password)

            viewLifecycleOwner.lifecycleScope.launch {
                profileVM.failure.collect { error ->
                    if (!error) successLoginMessage()
                }
            }
        }
        return view
    }

    private fun validatingInput(input: TextInputLayout) {
        when (input.editText?.text.toString().isBlank()) {
            true -> setInputError(input)
            false -> clearInputError(input)
        }
    }

    private fun setInputError(input: TextInputLayout) {
        input.error = "O campo ${input.hint} é obrigatório"
    }

    private fun clearInputError(input: TextInputLayout) {
        input.error = null
    }

    private fun successLoginMessage() {
        Snackbar.make(
            binding.profileScrollView,
            getString(R.string.login_success),
            Snackbar.LENGTH_SHORT
        ).show()
    }

}
