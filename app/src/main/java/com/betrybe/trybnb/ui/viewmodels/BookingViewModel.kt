package com.betrybe.trybnb.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.model.BookingId
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class BookingViewModel : ViewModel() {
    private val bookingRepository = BookingRepository()

    private val _bookings = MutableLiveData<List<Booking>>()
    val bookings: MutableLiveData<List<Booking>>
        get() = _bookings

    private val _fetchError = MutableStateFlow(false)
    val fetchError: StateFlow<Boolean>
        get() = _fetchError

    fun getBookings() {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val result = bookingRepository.getBookings()
            if (result.success) {
                _bookings.postValue(result.data!!)
            }
            if (!result.success) _fetchError.value = true

            ApiIdlingResource.decrement()
        }
    }
}