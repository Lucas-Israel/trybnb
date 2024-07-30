package com.betrybe.trybnb.data.network

import com.betrybe.trybnb.data.api.ApiService
import com.betrybe.trybnb.model.LoginBody
import com.betrybe.trybnb.model.Token
import javax.inject.Inject

class LoginDataSource @Inject constructor(private val service: ApiService) {

    suspend fun login(email: String, password: String): Token? {
        val loginBody = LoginBody(email, password)
        val loginResponse = service.login(loginBody)

        return loginResponse.body()
    }
}
