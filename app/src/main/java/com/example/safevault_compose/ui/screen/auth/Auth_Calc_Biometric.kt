package com.example.safevault_compose.ui.screen.auth

import android.os.Build
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import com.example.safevault_compose.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import androidx.core.content.ContextCompat
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth_Calc_Biometric(
    navController: NavHostController,
    activity: FragmentActivity // ✅ DIKIRIMKAN DARI NAVHOST
) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid
    val db = FirebaseDatabase.getInstance().reference

    var hasSaved by remember { mutableStateOf(false) }

    // ✅ Cek apakah data biometrik sudah tersimpan
    LaunchedEffect(Unit) {
        if (uid != null) {
            val snapshot = db.child("biometric").child(uid).get().await()
            hasSaved = snapshot.exists()
        }
    }

    // ✅ Fungsi untuk autentikasi biometrik
    fun startBiometricAuthentication(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(
            activity, // ✅ Gunakan activity, bukan cast context
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onError(errString.toString())
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onError("Autentikasi gagal, coba lagi.")
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verifikasi Wajah atau Sidik Jari")
            .setSubtitle("Gunakan Face ID atau Fingerprint Anda untuk lanjut")
            .setNegativeButtonText("Batal")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    // ✅ UI
    TopAppBar(
        modifier = Modifier.padding(top = 5.dp),
        title = { Text("Verifikasi Biometrik") },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(90.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_safe_vault_with_text),
            contentDescription = "Logo",
            modifier = Modifier
                .width(164.dp)
                .height(49.dp)
                .align(Alignment.Start),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Verify your biometrics",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(111.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_safe_vault),
            contentDescription = "biometric icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(136.dp)
                .height(136.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                if (uid != null) {
                    startBiometricAuthentication(
                        onSuccess = {
                            val data = mapOf(
                                "uid" to uid,
                                "email" to user?.email,
                                "deviceName" to Build.MODEL,
                                "status" to "verified",
                                "timestamp" to System.currentTimeMillis().toString()
                            )

                            db.child("biometric").child(uid).setValue(data)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Autentikasi sukses", Toast.LENGTH_SHORT).show()
                                    navController.navigate("note")
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Gagal simpan: ${it.message}", Toast.LENGTH_SHORT).show()
                                }
                        },
                        onError = { error ->
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = true
        ) {
            Text("Lanjutkan Autentikasi")
        }
    }
}
