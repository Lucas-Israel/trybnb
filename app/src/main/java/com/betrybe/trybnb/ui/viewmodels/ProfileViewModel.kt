package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {

    fun areFieldsFilled(login: String, password: String): Boolean {
        return login.isNotEmpty() && password.isNotEmpty()
    }
}