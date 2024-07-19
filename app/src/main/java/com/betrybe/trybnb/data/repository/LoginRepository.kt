package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.models.Response
import com.betrybe.trybnb.data.api.model.Token
import com.betrybe.trybnb.data.network.LoginDataSource
import java.net.ConnectException
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDS: LoginDataSource) {

    suspend fun login(email: String, password: String): Response<Token> {
        val response = loginDS.login(email, password)
        try {
            if (response?.token != null) {
                return Response(true, "", response)
            }
        } catch (e: ConnectException) {
            return Response(false, e.message.orEmpty(), null)
        }
        return Response(false, "Erro na autorização", null)
    }
}