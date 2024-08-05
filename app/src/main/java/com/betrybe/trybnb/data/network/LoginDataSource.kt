package com.betrybe.trybnb.data.network

import com.betrybe.trybnb.data.api.ApiService
import com.betrybe.trybnb.model.LoginBody
import com.betrybe.trybnb.data.response.Token
import javax.inject.Inject

class LoginDataSource @Inject constructor(private val service: ApiService) {

    /**
     * Performs a login request using the provided email and password.
     * @param email The user's email.
     * @param password The user's password.
     * @return A [Token] object containing the user's authentication token.
     */
    suspend fun login(email: String, password: String): Token {
        val loginBody = LoginBody(email, password)
        val loginResponse = service.login(loginBody)

        return loginResponse
    }
}
