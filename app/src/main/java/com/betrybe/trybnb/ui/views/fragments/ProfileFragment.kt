package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.utils.TextInputLayoutUtils.containerInputsValidationFlow
import com.betrybe.trybnb.databinding.FragmentProfileBinding
import com.betrybe.trybnb.ui.viewmodels.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileVM: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val binding = FragmentProfileBinding.bind(view)

        binding.loginButtonProfile.setOnClickListener {
            val areInputsValid = containerInputsValidationFlow(container!!)

            when (areInputsValid) {
                true -> {
                    profileVM.login(
                        email = binding.loginInputProfile.editText?.text.toString(),
                        password = binding.passwordInputProfile.editText?.text.toString()
                    )

                    successLoginMessage(binding)
                }

                false -> return@setOnClickListener
            }
        }
        return view
    }

    /**
     * Shows a Snackbar with a success message when a login is successful.
     */
    private fun successLoginMessage(binding: FragmentProfileBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            profileVM.token.collect { token ->
                token?.let {
                    Snackbar.make(
                        binding.profileScrollView,
                        getString(R.string.login_success),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}
