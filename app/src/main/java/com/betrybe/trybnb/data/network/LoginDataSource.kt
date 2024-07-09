package com.betrybe.trybnb.data.network

import android.util.Log
import com.betrybe.trybnb.data.api.ApiServiceClient
import com.betrybe.trybnb.data.models.LoginBody
import com.betrybe.trybnb.data.models.Token

class LoginDataSource {

    private val service = ApiServiceClient.instance

    suspend fun login(email: String, password: String): Token? {
        val loginBody = LoginBody(email, password)
        val loginResponse = service.login(loginBody)

        return loginResponse.body()
    }
}