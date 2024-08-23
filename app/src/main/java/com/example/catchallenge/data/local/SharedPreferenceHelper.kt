package com.example.catchallenge.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(
    @ApplicationContext context: Context
) {

    private val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun hasFetchedInitialData(): Boolean {
        return sharedPreferences.getBoolean("fetched_initial_data", false)
    }

    fun setFetchedInitialData(fetched: Boolean) {
        sharedPreferences.edit().putBoolean("fetched_initial_data", fetched).apply()
    }
}