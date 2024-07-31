package com.betrybe.trybnb.data.api


import com.betrybe.trybnb.data.response.BookingId
import com.betrybe.trybnb.data.response.CreateBooking
import com.betrybe.trybnb.model.LoginBody
import com.betrybe.trybnb.data.response.Reservation
import com.betrybe.trybnb.data.response.Token
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth")
    suspend fun login(@Body auth: LoginBody): Token

    @GET("booking")
    suspend fun getBookings(): List<BookingId>

    @GET("booking/{id}")
    @Headers("Accept: application/json")
    suspend fun getBookingById(@Path("id") id: String): Reservation

    @POST("booking")
    @Headers("Accept: application/json")
    suspend fun createBooking(@Body body: Reservation): CreateBooking
}
