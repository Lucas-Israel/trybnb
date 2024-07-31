package com.betrybe.trybnb.common.utils

import com.google.android.material.textfield.TextInputLayout

object TextInputLayoutUtils {

    fun validateEmptyInput(input: TextInputLayout, errorMessage: String) {
        when (input.editText?.text.toString().isBlank()) {
            true -> setInputError(input, errorMessage)
            false -> clearInputError(input)
        }
    }

    private fun setInputError(input: TextInputLayout, errorMessage: String) {
        input.error = errorMessage
    }

    private fun clearInputError(input: TextInputLayout) {
        input.error = null
    }
}
