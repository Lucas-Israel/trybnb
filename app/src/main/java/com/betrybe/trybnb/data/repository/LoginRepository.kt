package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.utils.ClientResult
import com.betrybe.trybnb.data.response.Token
import com.betrybe.trybnb.data.network.LoginDataSource
import com.betrybe.trybnb.data.utils.safeApiCall
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDS: LoginDataSource) {

    suspend fun login(email: String, password: String): ClientResult<Token> {

        return safeApiCall {
            loginDS.login(email, password)
        }
    }
}
