package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.models.ClientResult
import com.betrybe.trybnb.data.api.model.Token
import com.betrybe.trybnb.data.network.LoginDataSource
import com.betrybe.trybnb.data.repository.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import java.net.ConnectException
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDS: LoginDataSource) {

    suspend fun login(email: String, password: String): ClientResult<Token> {

        return withContext(DispatcherProvider.IO) {
            val response =
                loginDS.login(email, password) ?: return@withContext ClientResult.ClientError(
                    isServerError = false,
                    isNetworkError = true
                )
            
            return@withContext ClientResult.ClientSuccess(response)
        }
    }
}