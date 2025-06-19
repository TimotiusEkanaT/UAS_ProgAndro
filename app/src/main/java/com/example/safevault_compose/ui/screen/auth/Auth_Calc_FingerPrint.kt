package com.example.safevault_compose.ui.screen.auth

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safevault_compose.R
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.tasks.await
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth_Calc_FingerPrint(navController: NavHostController) {
    val context = LocalContext.current

    TopAppBar(
        modifier = Modifier.padding(top = 5.dp),
        title = { Text("Fingerprint") },
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
            text = "Place your finger on the sensor",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(111.dp))

        Image(
            painter = painterResource(id = R.drawable.fingerprint),
            contentDescription = "Fingerprint Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(136.dp)
                .height(136.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                val user = FirebaseAuth.getInstance().currentUser
                val uid = user?.uid
                val db = FirebaseDatabase.getInstance().reference

                if (uid != null) {
                    val data = mapOf(
                        "uid" to uid,
                        "email" to user.email,
                        "deviceName" to Build.MODEL,
                        "status" to "registered",
                        "timestamp" to System.currentTimeMillis().toString()
                    )
                    db.child("biometric_fingerprint").child(uid).setValue(data)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Fingerprint disimpan", Toast.LENGTH_SHORT).show()
                            navController.navigate("auth_combination")
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Gagal simpan: ${it.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(context, "User tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Lanjutkan")
        }
    }
}
