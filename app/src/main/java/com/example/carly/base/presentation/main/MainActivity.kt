package com.example.carly.base.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.carly.base.presentation.main.ui.theme.CarlyTheme
import com.example.carly.cars.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarlyTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
