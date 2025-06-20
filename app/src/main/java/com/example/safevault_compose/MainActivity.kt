package com.example.safevault_compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.example.safevault_compose.navigation.AppNavHost
import com.example.safevault_compose.ui.theme.SafeVault_ComposeTheme
import com.example.safevault_compose.utils.SecurePrefs
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class MainActivity : FragmentActivity() {

    companion object {
        lateinit var callbackManager: CallbackManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi Facebook & Firebase
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)
        callbackManager = CallbackManager.Factory.create()
        FirebaseApp.initializeApp(this)

        val context = this

        // Cek login dan status kombinasi di Firebase
        CoroutineScope(Dispatchers.Main).launch {
            val user = FirebaseAuth.getInstance().currentUser
            var startDestination = "auth_calc"

            if (user != null) {
                val uid = user.uid
                val db = FirebaseDatabase.getInstance().reference
                val combinationSnapshot = withContext(Dispatchers.IO) {
                    db.child("calculator_combination").child(uid).get().await()
                }

                startDestination = if (combinationSnapshot.exists()) {
                    "calculator"
                } else {
                    "auth_combination"
                }
            }

            setContent {
                SafeVault_ComposeTheme {
                    AppNavHost(startDestination = startDestination)
                }
            }
        }
    }

    @Deprecated("Required for Facebook SDK login flow")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}

