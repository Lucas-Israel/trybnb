package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.trybnb.data.api.model.Book
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.ClientResult
import com.betrybe.trybnb.data.repository.BookingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
            when (val result = bookingRepository.getBookings()) {
                is ClientResult.ClientSuccess -> _bookings.postValue(result.data)
                is ClientResult.ClientError -> _fetchError.value = true
            }
        }
    }

    fun createBooking(body: Book) {
        viewModelScope.launch {
            when (bookingRepository.createBooking(body)) {
                is ClientResult.ClientSuccess -> _isBookingCreationSuccess.value = true
                is ClientResult.ClientError -> _isBookingCreationSuccess.value = false
            }
        }
    }
}
