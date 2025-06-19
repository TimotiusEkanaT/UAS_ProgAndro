package com.example.safevault_compose.ui.screen.setting.combination

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safevault_compose.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting_Calc_Combination_Auth(navController: NavHostController) {
    var combination by remember { mutableStateOf("") }
    var lastSavedKey by remember { mutableStateOf<String?>(null) } // ✅ Untuk hapus
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    val dbRef = FirebaseDatabase.getInstance().getReference("calculator_combination")
    val context = LocalContext.current

    val buttonSize = 72.dp
    val calcButtons = listOf(
        listOf("C", "⌫", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "−"),
        listOf("1", "2", "3", "+"),
        listOf("🧮", "0", ".", "=")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (uid != null && lastSavedKey != null) {
                                dbRef.child(uid).child(lastSavedKey!!).removeValue()
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "Kombinasi dihapus", Toast.LENGTH_SHORT).show()
                                        combination = ""
                                        lastSavedKey = null
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(context, "Gagal hapus: ${it.message}", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                Toast.makeText(context, "Tidak ada kombinasi yang bisa dihapus", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_safe_vault_with_text),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(160.dp)
                    .height(48.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Enter your calculator combination to unlock SafeVault",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(36.dp))

            OutlinedTextField(
                value = combination,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                textStyle = TextStyle(fontSize = 20.sp),
                readOnly = true,
                label = { Text("Combination") },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
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
                                            if (uid != null && combination.isNotBlank()) {
                                                val key = dbRef.child(uid).push().key
                                                if (key != null) {
                                                    val data = mapOf("value" to combination)
                                                    dbRef.child(uid).child(key).setValue(data)
                                                        .addOnSuccessListener {
                                                            Toast.makeText(context, "Combination saved", Toast.LENGTH_SHORT).show()
                                                            lastSavedKey = key // ✅ simpan kunci untuk delete
                                                            navController.navigate("setting_combination")
                                                        }
                                                        .addOnFailureListener {
                                                            Toast.makeText(context, "Gagal menyimpan: ${it.message}", Toast.LENGTH_SHORT).show()
                                                        }
                                                }
                                            } else {
                                                Toast.makeText(context, "Combination kosong", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                        "🧮" -> { /* opsional */ }
                                        else -> if (combination.length < 12) combination += label
                                    }
                                },
                                modifier = Modifier.size(buttonSize),
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
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
