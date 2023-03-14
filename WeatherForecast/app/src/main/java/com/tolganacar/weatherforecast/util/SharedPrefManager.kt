package com.tolganacar.weatherforecast.util

import android.content.SharedPreferences

class SharedPrefManager(private val sharedPreferences: SharedPreferences) {

    fun getString(key: String, defValue: String): String {
        return sharedPreferences.getString(key, defValue) ?: defValue
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}