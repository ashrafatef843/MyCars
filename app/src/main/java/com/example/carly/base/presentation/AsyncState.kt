package com.example.carly.base.presentation

/**
 * A sealed class that represents the network request in the presentation layer as:
 * - Uninitialized
 * - Loading
 * - Success
 * - Fail
 */
sealed class AsyncState<out T>(private val value: T?) {
    /**
     * Returns the value or null.
     *
     * Success always have a value. Loading and Fail can also return a value which is useful for
     * pagination or progressive data loading.
     *
     * Can be invoked as an operator like: `yourProp()`
     */
    open operator fun invoke(): T? = value
}

object Uninitialized : AsyncState<Nothing>(value = null)

data class Loading<out T>(private val value: T? = null) : AsyncState<T>(value = value)

data class Success<out T>(private val value: T) : AsyncState<T>(value = value) {
    override operator fun invoke(): T = value
}

data class Fail<out T>(
    val error: Exception,
    private val value: T? = null,
) : AsyncState<T>(value = value) {
    override fun equals(other: Any?): Boolean {
        if (other !is Fail<*>) return false

        val otherError = other.error
        return error::class == otherError::class &&
            error.message == otherError.message &&
            error.stackTrace.firstOrNull() == otherError.stackTrace.firstOrNull()
    }

    override fun hashCode(): Int = arrayOf(error::class, error.message, error.stackTrace.firstOrNull()).contentHashCode()
}
