package com.betrybe.trybnb.common.utils

import android.content.Context
import androidx.annotation.StringRes

/**
 * Helper class to extract strings from resources.
 */
sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    /**
     * Returns the string value
     * @param context Context to use for resource resolution
     */
    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}
