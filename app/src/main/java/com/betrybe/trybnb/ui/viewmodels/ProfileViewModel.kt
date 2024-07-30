package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.trybnb.data.utils.ClientResult
import com.betrybe.trybnb.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    private var _token = MutableLiveData("")
    val token: MutableLiveData<String>
        get() = _token

    private var _loginFailure = MutableStateFlow(true)
    val loginFailure: MutableStateFlow<Boolean>
        get() = _loginFailure

    fun login(email: String, password: String) {

        viewModelScope.launch {
            when (val login = loginRepository.login(email, password)) {
                is ClientResult.ClientSuccess -> {
                    _loginFailure.value = false
                    _token.postValue(login.data?.token)
                }
                is ClientResult.ClientError -> {
                    _loginFailure.value = true
                }
            }
        }
    }

}
