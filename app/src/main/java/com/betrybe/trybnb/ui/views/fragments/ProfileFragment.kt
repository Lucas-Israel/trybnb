package com.betrybe.trybnb.ui.views.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.betrybe.trybnb.R
import com.betrybe.trybnb.UiText
import com.betrybe.trybnb.common.utils.TextInputLayoutUtils.validateEmptyInput
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
            val emailInput = binding.loginInputProfile
            val passInput = binding.passwordInputProfile
            val email = emailInput.editText?.text.toString()
            val password = passInput.editText?.text.toString()
            val context = container?.context

            if (context != null) {
                val emailEmptyWarning = UiText.StringResource(R.string.email_empty_warning).asString(context)
                val passwordEmptyWarning = UiText.StringResource(R.string.password_empty_warning).asString(context)

                validateEmptyInput(emailInput, emailEmptyWarning)
                validateEmptyInput(passInput, passwordEmptyWarning)
            }

            if (email.isEmpty() || password.isEmpty()) return@setOnClickListener

            profileVM.login(email, password)

            viewLifecycleOwner.lifecycleScope.launch {
                profileVM.loginFailure.collect { error ->
                    if (!error) successLoginMessage(binding)
                }
            }
        }
        return view
    }

    private fun successLoginMessage(binding: FragmentProfileBinding) {
        Snackbar.make(
            binding.profileScrollView,
            getString(R.string.login_success),
            Snackbar.LENGTH_SHORT
        ).show()
    }

}
