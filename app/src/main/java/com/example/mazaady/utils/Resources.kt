package com.example.mazaady.utils

sealed class Resources<out T> {
    data class Success<T>(val data: T) : Resources<T>()
    data class Error(val message: String) : Resources<Nothing>()
    object Loading : Resources<Nothing>()

    fun toData(): T? = if (this is Success) data else null
    fun toError(): String? = if (this is Error) message else null
}