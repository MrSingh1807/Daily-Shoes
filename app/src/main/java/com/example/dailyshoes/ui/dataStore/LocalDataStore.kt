package com.example.dailyshoes.ui.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Validate_User")

@ViewModelScoped
class LocalDataStore @Inject constructor(@ApplicationContext private val mContext: Context) {

    val PREFERENCES_IS_LOGIN = "IS_LOGIN"

    suspend fun saveIsLogin(isLogin: Boolean) {
        mContext.dataStore.edit {
            it[booleanPreferencesKey(PREFERENCES_IS_LOGIN)] = isLogin
        }
    }

    val checkIsLogin: Flow<Boolean> = mContext.dataStore.data.catch {
        if (it is IOException) emit(emptyPreferences())
        else throw it
    }.map { pref ->
        pref[booleanPreferencesKey(PREFERENCES_IS_LOGIN)] ?: false
    }
}