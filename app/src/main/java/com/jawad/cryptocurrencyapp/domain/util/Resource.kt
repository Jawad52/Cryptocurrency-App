package com.jawad.cryptocurrencyapp.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message?.let {
        message
    } ?: run {
        "Sorry something went wrong, please try again"
    })
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
