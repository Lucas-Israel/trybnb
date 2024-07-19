package com.betrybe.trybnb.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.api.model.BookingId
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.ClientResult
import com.betrybe.trybnb.data.repository.BookingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val bookingRepository: BookingRepository) : ViewModel() {

    private val _bookings = MutableLiveData<List<Booking>>()
    val bookings: MutableLiveData<List<Booking>>
        get() = _bookings

    private val _fetchError = MutableStateFlow(false)
    val fetchError: StateFlow<Boolean>
        get() = _fetchError

    private val _isBookingCreationSuccess = MutableStateFlow(false)
    val isBookingCreationSuccess: StateFlow<Boolean>
        get() = _isBookingCreationSuccess

    fun getBookings() {
        viewModelScope.launch {
            ApiIdlingResource.increment()
            val result = bookingRepository.getBookings()
            if (result is ClientResult.ClientSuccess) {
                _bookings.postValue(result.data)
            }
            if (result is ClientResult.ClientError) _fetchError.value = true

            ApiIdlingResource.decrement()
        }
    }

    fun createBooking(body: Book) {
        viewModelScope.launch {
            ApiIdlingResource.increment()
            val result = bookingRepository.createBooking(body)

            if (result is ClientResult.ClientSuccess) _isBookingCreationSuccess.value = true
            ApiIdlingResource.decrement()
        }
    }
}