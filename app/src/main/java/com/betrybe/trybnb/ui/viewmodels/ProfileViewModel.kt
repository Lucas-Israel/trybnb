package com.betrybe.trybnb.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.models.ClientResult
import com.betrybe.trybnb.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    private var _token = MutableLiveData("")
    val token: MutableLiveData<String>
        get() = _token

    private var _loginFailure = MutableStateFlow(true)
    val failure: MutableStateFlow<Boolean>
        get() = _loginFailure

    fun login(email: String, password: String) {
        viewModelScope.launch {
            ApiIdlingResource.increment()
            val login = loginRepository.login(email, password)
            if (login is ClientResult.ClientSuccess) {
                _loginFailure.value = false
                _token.postValue(login.data.token)
            } else {
                _loginFailure.value = true
            }
            ApiIdlingResource.decrement()
        }
    }


}