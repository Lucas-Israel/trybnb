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

        val loginInput = binding.loginInputProfile
        val passInput = binding.passwordInputProfile
        val email = loginInput.editText?.text.toString()
        val password = passInput.editText?.text.toString()

        binding.loginButtonProfile.setOnClickListener {
            isInputEmpty(loginInput)
            isInputEmpty(passInput)

            profileVM.login(email, password)

            viewLifecycleOwner.lifecycleScope.launch {
                profileVM.failure.collect { error ->
                    if (!error) successLoginMessage()
                }
            }
        }
        return view
    }

    private fun isInputEmpty(input: TextInputLayout): Boolean {
        if (input.editText?.text?.isEmpty() == true) {
            input.error = "O campo ${input.hint} é obrigatório"
            return true
        } else {
            input.error = null
            return false
        }
    }

    private fun successLoginMessage() {
        Snackbar.make(
            binding.profileScrollView,
            getString(R.string.login_success),
            Snackbar.LENGTH_SHORT
        ).show()
    }

}
