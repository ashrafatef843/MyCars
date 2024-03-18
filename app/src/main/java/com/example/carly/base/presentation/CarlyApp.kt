package com.example.carly.base.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarlyApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}