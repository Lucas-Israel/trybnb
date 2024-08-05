package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.common.utils.ClientResult
import com.betrybe.trybnb.data.response.Token
import com.betrybe.trybnb.data.network.LoginDataSource
import com.betrybe.trybnb.common.utils.safeApiCall
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDS: LoginDataSource) {

    /**
     * Logs in a user with the provided email and password.
     * @param email The user's email.
     * @param password The user's password.
     * @return A [Token] object containing the user's authentication token.
     */
    suspend fun login(email: String, password: String): ClientResult<Token> {
        return safeApiCall {
            loginDS.login(email, password)
        }
    }
}
