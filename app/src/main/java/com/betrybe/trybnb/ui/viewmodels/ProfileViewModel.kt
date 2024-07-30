package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.utils.ClientResult
import com.betrybe.trybnb.data.repository.LoginRepository
import com.google.android.material.textfield.TextInputLayout
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
    val failure: MutableStateFlow<Boolean>
        get() = _loginFailure

    fun login(loginInput: TextInputLayout, passInput: TextInputLayout) {
        val email = loginInput.editText?.text.toString()
        val password = passInput.editText?.text.toString()

        isInputEmpty(loginInput)
        isInputEmpty(passInput)

        viewModelScope.launch {
            ApiIdlingResource.increment()
            when (val login = loginRepository.login(email, password)) {
                is ClientResult.ClientSuccess -> {
                    _loginFailure.value = false
                    _token.postValue(login.data?.token)
                }
                is ClientResult.ClientError -> {
                    _loginFailure.value = true
                }
            }
            ApiIdlingResource.decrement()
        }
    }

    private fun isInputEmpty(input: TextInputLayout) {
        if (input.editText?.text?.isEmpty() == true) {
            _loginFailure.value = true
            input.error = "O campo ${input.hint} é obrigatório"
        } else {
            input.error = null
        }
    }

}
