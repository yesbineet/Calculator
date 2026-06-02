//package com.example.calculator
//
//package com.example.calculator
//import android.content.Context
//import androidx.datastore.preferences.core.booleanPreferencesKey
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//val Context.dataStore by preferencesDataStore("settings")
//
//class ThemePreferences(private val context: Context) {
//
//    companion object {
//        val DARK_MODE = booleanPreferencesKey("dark_mode")
//    }
//
//    val darkModeFlow: Flow<Boolean> =
//        context.dataStore.data.map {
//            it[DARK_MODE] ?: false
//        }
//
//    suspend fun saveTheme(enabled: Boolean) {
//        context.dataStore.edit {
//            it[DARK_MODE] = enabled
//        }
//    }
//}