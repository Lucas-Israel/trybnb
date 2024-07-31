package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.trybnb.model.Booking
import com.betrybe.trybnb.common.utils.ClientResult
import com.betrybe.trybnb.data.repository.BookingRepository
import com.betrybe.trybnb.data.response.Reservation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val bookingRepository: BookingRepository) : ViewModel() {

    private val _bookings = MutableStateFlow<List<Booking>>(listOf())
    val bookings: MutableStateFlow<List<Booking>>
        get() = _bookings

    private val _fetchError = MutableStateFlow(false)
    val fetchError: StateFlow<Boolean>
        get() = _fetchError

    private val _isBookingCreationSuccess = MutableStateFlow(false)
    val isBookingCreationSuccess: StateFlow<Boolean>
        get() = _isBookingCreationSuccess

    fun getBookings() {
        viewModelScope.launch {
            when (val result = bookingRepository.getBookings()) {
                is ClientResult.ClientSuccess -> {
                    _bookings.value = result.data
                    _fetchError.value = false
                }
                is ClientResult.ClientError -> {
                    // Handle error
                    _fetchError.value = true
                }
                // is ClientResult.ClientAnotherError -> handle as necessary
            }
        }
    }

    fun createBooking(body: Reservation) {
        viewModelScope.launch {
            when (bookingRepository.createBooking(body)) {
                is ClientResult.ClientSuccess -> _isBookingCreationSuccess.value = true
                is ClientResult.ClientError -> {
                    // Handle error
                    _isBookingCreationSuccess.value = false
                }
                // is ClientResult.ClientAnotherError -> handle as necessary
            }
        }
    }
}
