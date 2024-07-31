package com.betrybe.trybnb.common.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object DispatcherProvider { val IO: CoroutineContext = Dispatchers.IO }
