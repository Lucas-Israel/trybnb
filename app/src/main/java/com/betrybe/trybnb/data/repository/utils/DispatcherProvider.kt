package com.betrybe.trybnb.data.repository.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object DispatcherProvider { val IO: CoroutineContext = Dispatchers.IO }