package com.example.carly.base.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {
    var state by mutableStateOf(getInitialState())
        private set

    fun setState(reducer: (currentState: T) -> T) {
        val currentValue = state
        state = reducer(currentValue)
    }

    abstract fun getInitialState(): T
}
