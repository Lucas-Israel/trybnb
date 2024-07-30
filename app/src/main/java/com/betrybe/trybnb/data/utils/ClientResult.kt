package com.betrybe.trybnb.data.utils

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