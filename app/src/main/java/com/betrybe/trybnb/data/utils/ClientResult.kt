package com.betrybe.trybnb.data.utils

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class ClientResult<out T> {

    /**
     * Creates a new response object for a successful response
     * @param data the response data.
     */
    data class ClientSuccess<T>(val data: T) : ClientResult<T>()

    /**
     * Exception when the service response results in error
     * @param isServerError if the error was in the server
     * @param isNetworkError if the error is related to the network
     */
    data class ClientError(val isServerError: Boolean, val isNetworkError: Boolean) :
        ClientResult<Nothing>()
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): ClientResult<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall()
            ClientResult.ClientSuccess(response)
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> {
                    // Rethrow cancellation exception to ensure coroutines are cancelled correctly
                    throw e
                }

                is HttpException -> {
                    // Handle HTTP errors, assuming it's a server error
                    ClientResult.ClientError(
                        isNetworkError = true,
                        isServerError = false
                    )
                }

                is IOException -> {
                    // Handle network errors (e.g., no connectivity)
                    ClientResult.ClientError(
                        isNetworkError = false,
                        isServerError = true
                    )
                }

                else -> {
                    // Handle any other unexpected exceptions
                    ClientResult.ClientError(
                        isNetworkError = true,
                        isServerError = false
                    )
                }
            }
        }
    }
}
