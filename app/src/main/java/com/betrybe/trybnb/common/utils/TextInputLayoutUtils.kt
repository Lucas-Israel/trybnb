package com.betrybe.trybnb.common.utils

import android.view.ViewGroup
import androidx.core.view.allViews
import com.betrybe.trybnb.R
import com.google.android.material.textfield.TextInputLayout

/**
 * Helper object for TextInputLayout validation
 */
object TextInputLayoutUtils {

    /**
     * Validation flow for TextInputs inside a container.
     * Searches for all TextInputLayouts in the given ViewGroup and validates their contents.
     * Validates if inputs are empty and validates if checkIn and checkOut are in the correct format.
     * @param container The container ViewGroup to search for TextInputLayouts.
     * @return True if all TextInputLayouts are valid, false otherwise.
     */
    fun containerInputsValidationFlow(container: ViewGroup): Boolean {
        var allValid = true

        container.allViews.filterIsInstance<TextInputLayout>().forEach {
            val areInputsBlank = validateEmptyInput(it)
            val checkInOrCheckoutInput =
                it.id == R.id.checkin_create_reservation || it.id == R.id.checkout_create_reservation

            if (areInputsBlank) allValid = false

            if (!areInputsBlank && checkInOrCheckoutInput) {
                validateWrongDateFormat(it)
                allValid = false
            }
        }

        return allValid
    }

    /**
     * Validates if the input is empty, setting an error message if it is.
     * @param input The TextInputLayout to validate.
     * @return True if the input is empty, false otherwise.
     */
    private fun validateEmptyInput(input: TextInputLayout): Boolean {

        when (input.editText?.text.toString().isBlank()) {
            true -> {
                setInputError(
                    input,
                    inputErrorMessageExtractor(R.string.empty_input_warning, input)
                )
                return true
            }

            false -> {
                clearInputError(input)
                return false
            }
        }
    }

    /**
     * Validates if the input is in the correct date format, setting an error message if it is not.
     * @param input The TextInputLayout to validate.
     * @return True if the input is not in the correct format, false otherwise.
     */
    private fun validateWrongDateFormat(input: TextInputLayout): Boolean {

        val formattedText = DateFormatterUtils.reformatDate(input.editText?.text.toString())

        when (formattedText) {
            null -> {
                setInputError(
                    input,
                    inputErrorMessageExtractor(R.string.date_format_warning, input)
                )
                return true
            }

            else -> {
                clearInputError(input)
                return false
            }
        }
    }

    /**
     * Sets the error message for a TextInputLayout.
     * Used in the TextInputLayout validation functions.
     * @param input The TextInputLayout to set the error message for.
     */
    private fun setInputError(input: TextInputLayout, errorMessage: String) {
        input.error = errorMessage
    }

    /**
     * Clears the error message for a TextInputLayout.
     * Used in the TextInputLayout validation functions.
     * @param input The TextInputLayout to clear the error message from.
     */
    private fun clearInputError(input: TextInputLayout) {
        input.error = null
    }

    /**
     * Extracts the error message for a TextInputLayout.
     * @param rId The resource ID for the error message.
     * @param input The TextInputLayout to extract the error message from.
     * @return The error message as a String.
     */
    private fun inputErrorMessageExtractor(rId: Int, input: TextInputLayout): String {
        return UiText.StringResource(rId, input.hint.toString())
            .asString(input.context)
    }

}
