package org.sacada.network

sealed class NetworkResult<out T> {
    data class Success<T>(
        val data: T,
    ) : NetworkResult<T>()

    data class Error(
        val message: String,
        val exception: Throwable? = null,
    ) : NetworkResult<Nothing>()

    val isSuccess: Boolean
        get() = this is Success

    val isError: Boolean
        get() = this is Error

    fun getOrNull(): T? = (this as? Success)?.data

    fun exceptionOrNull(): Throwable? = (this as? Error)?.exception
}
