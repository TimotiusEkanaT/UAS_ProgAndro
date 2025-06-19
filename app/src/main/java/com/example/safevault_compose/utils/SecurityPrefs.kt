// 📁 utils/SecurePrefs.kt
package com.example.safevault_compose.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SecurePrefs {

    private const val FILE_NAME = "safevault_secure_prefs"

    private fun getPrefs(context: Context) =
        EncryptedSharedPreferences.create(
            context,
            FILE_NAME,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveLoginStatus(context: Context, isLoggedIn: Boolean) {
        getPrefs(context).edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPrefs(context).getBoolean("is_logged_in", false)
    }

    fun saveUserEmail(context: Context, email: String) {
        getPrefs(context).edit().putString("user_email", email).apply()
    }

    fun getUserEmail(context: Context): String? {
        return getPrefs(context).getString("user_email", null)
    }

    fun clear(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}
