package com.betrybe.trybnb.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.models.BookingId
import com.betrybe.trybnb.data.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {
    private val bookingRepository = BookingRepository()

    private val _bookings = MutableStateFlow(listOf(BookingId(0)))
    val bookings: StateFlow<List<BookingId>>
        get() = _bookings

    private val _fetchError = MutableStateFlow(false)
    val fetchError : StateFlow<Boolean>
        get() = _fetchError

    fun getBookings() {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val result = bookingRepository.getBookings()
            if (result.success) _bookings.value = result.data!!
            if (!result.success) _fetchError.value = true

            ApiIdlingResource.decrement()
        }
    }
}