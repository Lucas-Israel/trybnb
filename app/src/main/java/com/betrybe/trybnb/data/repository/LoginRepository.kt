package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.models.ClientResult
import com.betrybe.trybnb.data.api.model.Token
import com.betrybe.trybnb.data.network.LoginDataSource
import java.net.ConnectException
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDS: LoginDataSource) {

    suspend fun login(email: String, password: String): ClientResult<Token> {
        try {
            val response = loginDS.login(email, password) ?: throw ConnectException()
            return ClientResult.ClientSuccess(response)
        } catch (e: ConnectException) {
            return ClientResult.ClientError(isServerError = false, isNetworkError = true)
        }
    }
}