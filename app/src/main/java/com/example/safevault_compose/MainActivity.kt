package com.example.safevault_compose

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity // ⬅️ Ganti dari ComponentActivity
import androidx.activity.compose.setContent
import com.example.safevault_compose.navigation.AppNavHost
import com.example.safevault_compose.ui.theme.SafeVault_ComposeTheme
import com.example.safevault_compose.utils.SecurePrefs
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp

class MainActivity : FragmentActivity() { // ⬅️ Ganti ke FragmentActivity

    companion object {
        lateinit var callbackManager: CallbackManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi Facebook SDK
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)
        callbackManager = CallbackManager.Factory.create()

        // Inisialisasi Firebase
        FirebaseApp.initializeApp(this)
        val context = this
        val startDestination = if (SecurePrefs.isLoggedIn(context)) {
            "auth_faceid"
        } else {
            "auth_calc"
        }

        setContent {
            SafeVault_ComposeTheme {
                AppNavHost(startDestination = startDestination)
            }
        }
    }

    @Deprecated("Required for Facebook SDK login flow")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
