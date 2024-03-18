package com.example.carly.base.util

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class AssetReader
    @Inject
    constructor(
        @ApplicationContext
        private val context: Context
    ) {
        fun readJSONFromAssets(path: String): String {
            val identifier = "[ReadJSON]"
            try {
                val file = context.assets.open(path)
                val bufferedReader = BufferedReader(InputStreamReader(file))
                val stringBuilder = StringBuilder()
                bufferedReader.useLines { lines ->
                    lines.forEach {
                        stringBuilder.append(it)
                    }
                }
                val jsonString = stringBuilder.toString()
                return jsonString
            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }
        }
    }
