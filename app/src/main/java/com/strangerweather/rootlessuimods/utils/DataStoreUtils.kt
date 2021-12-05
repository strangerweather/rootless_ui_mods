package com.strangerweather.rootlessuimods.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object DataStoreUtils {

    private val Context.myDataStore by preferencesDataStore("Settings")

     suspend fun saveMonetChoice(context: Context, key: String, value: Boolean) {
        val monetChoiceKey = booleanPreferencesKey(key)
        context.myDataStore.edit { settings ->
            settings[monetChoiceKey] = value
        }
    }

     suspend fun getMonetChoice(context: Context, key: String): Boolean? {
        val monetChoiceKey = booleanPreferencesKey(key)
        val monetPref = context.myDataStore.data.first()
        return monetPref[monetChoiceKey]
    }
}