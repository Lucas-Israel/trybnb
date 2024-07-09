package com.betrybe.trybnb.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    private var _token = MutableStateFlow("")
    val token: StateFlow<String>
        get() = _token

    private var _loginFailure = MutableStateFlow(false)
    val failure: StateFlow<Boolean>
        get() = _loginFailure

    fun login(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val login = loginRepository.login(email, password)
            if (login.success) _token.value = login.data?.token!!
            if (!login.success) _loginFailure.value = true
            ApiIdlingResource.decrement()
        }
    }


}