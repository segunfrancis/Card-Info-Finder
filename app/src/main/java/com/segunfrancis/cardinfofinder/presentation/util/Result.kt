package com.segunfrancis.cardinfofinder.presentation.util

import com.segunfrancis.cardinfofinder.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Sealed class used for managing transaction states
 */

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()

    object Loading: Result<Nothing>()

    data class Error(val error: Throwable): Result<Nothing>() {
        val formattedErrorMessage = when (error) {
            is UnknownHostException -> {
                R.string.error_message_no_internet
            }
            is SocketTimeoutException -> {
                R.string.error_message_timeout
            }
            is HttpException -> {
                when {
                    error.code() == 404 -> {
                        R.string.error_message_404
                    }
                    error.code() == 429 -> {
                        R.string.error_message_429
                    }
                    else -> {
                        R.string.error_message_generic
                    }
                }
            }
            else -> {
                R.string.error_message_generic
            }
        }
    }
}