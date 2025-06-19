package com.example.safevault_compose.ui.screen.setting.combination

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safevault_compose.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting_Calc_Combination_Submenu(navController: NavHostController, id: String?) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    val context = LocalContext.current
    val dbRef = FirebaseDatabase.getInstance().getReference("calculator_combination")

    var combination by remember { mutableStateOf("") }

    val buttonSize = 72.dp
    val calcButtons = listOf(
        listOf("C", "⌫", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "−"),
        listOf("1", "2", "3", "+"),
        listOf("🧮", "0", ".", "=")
    )

    // 🔄 Load data existing jika ID tidak null
    LaunchedEffect(id) {
        if (uid != null && id != null) {
            val snapshot = dbRef.child(uid).child(id).child("value").get().await()
            combination = snapshot.getValue(String::class.java) ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 5.dp),
                title = { Text("Setting") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_safe_vault_with_text),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(160.dp)
                    .height(48.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = combination,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onTertiary,
                ),
                readOnly = true,
                label = { Text("Combination") },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    disabledBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                calcButtons.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        row.forEach { label ->
                            val isOperator = label in listOf("÷", "×", "−", "+", "=", "C", "%")
                            Button(
                                onClick = {
                                    when (label) {
                                        "C" -> combination = ""
                                        "⌫" -> if (combination.isNotEmpty()) combination = combination.dropLast(1)
                                        "=" -> {
                                            if (uid != null && id != null) {
                                                val data = mapOf("value" to combination)
                                                dbRef.child(uid).child(id).setValue(data).addOnSuccessListener {
                                                    Toast.makeText(context, "Kombinasi berhasil diubah", Toast.LENGTH_SHORT).show()
                                                    navController.popBackStack()
                                                }.addOnFailureListener {
                                                    Toast.makeText(context, "Gagal mengubah kombinasi", Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        }
                                        "🧮" -> { /* Tambahkan logika scientific jika ada */ }
                                        else -> if (combination.length < 12) combination += label
                                    }
                                },
                                modifier = Modifier.size(buttonSize),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isOperator) Color.White else Color.White
                                ),
                                shape = CircleShape,
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                when (label) {
                                    "⌫" -> Image(
                                        painter = painterResource(id = R.drawable.delete),
                                        contentDescription = "Delete",
                                        modifier = Modifier.size(20.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                    "🧮" -> Image(
                                        painter = painterResource(id = R.drawable.scientific),
                                        contentDescription = "Scientific",
                                        modifier = Modifier.size(20.dp)
                                    )
                                    else -> Text(
                                        text = label,
                                        fontSize = 20.sp,
                                        color = if (isOperator) MaterialTheme.colorScheme.primary else Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
