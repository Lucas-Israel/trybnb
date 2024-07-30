package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.utils.ClientResult
import com.betrybe.trybnb.data.response.Token
import com.betrybe.trybnb.data.network.LoginDataSource
import com.betrybe.trybnb.data.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDS: LoginDataSource) {

    suspend fun login(email: String, password: String): ClientResult<Token?> {

        return withContext(DispatcherProvider.IO) {
            val response = loginDS.login(email, password)

            if (response?.token == null || response.token.isEmpty()) {
                return@withContext ClientResult.ClientError(
                    isServerError = false,
                    isNetworkError = true
                )
            }
            
            return@withContext ClientResult.ClientSuccess(response)
        }
    }
}
