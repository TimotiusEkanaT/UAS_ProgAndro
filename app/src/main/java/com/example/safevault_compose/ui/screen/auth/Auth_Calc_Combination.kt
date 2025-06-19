package com.example.safevault_compose.ui.screen.auth

import android.os.Build
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safevault_compose.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth_Calc_Combination(navController: NavHostController) {
    var combination by remember { mutableStateOf("") }
    val context = LocalContext.current

    val buttonSize = 72.dp
    val calcButtons = listOf(
        listOf("C", "⌫", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "−"),
        listOf("1", "2", "3", "+"),
        listOf("🧮", "0", ".", "=")
    )

    TopAppBar(
        modifier = Modifier.padding(top = 5.dp),
        title = { Text("Calculator Combination") },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(50.dp))

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
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = combination,
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp)
                .padding(horizontal = 10.dp),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary,
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
                                        val user = FirebaseAuth.getInstance().currentUser
                                        val uid = user?.uid
                                        val db = FirebaseDatabase.getInstance().reference

                                        if (uid != null && combination.isNotBlank()) {
                                            val data = mapOf(
                                                "uid" to uid,
                                                "email" to user.email,
                                                "deviceName" to Build.MODEL,
                                                "combination" to combination,
                                                "timestamp" to System.currentTimeMillis().toString()
                                            )
                                            db.child("calculator_combination").child(uid).setValue(data)
                                                .addOnSuccessListener {
                                                    Toast.makeText(context, "Combination disimpan", Toast.LENGTH_SHORT).show()
                                                    navController.navigate("calculator")
                                                }
                                                .addOnFailureListener {
                                                    Toast.makeText(context, "Gagal menyimpan: ${it.message}", Toast.LENGTH_SHORT).show()
                                                }
                                        } else {
                                            Toast.makeText(context, "Kombinasi tidak boleh kosong", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    "🧮" -> {}
                                    else -> if (combination.length < 12) combination += label
                                }
                            },
                            modifier = Modifier.size(buttonSize),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
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
                                    color = if (isOperator) MaterialTheme.colorScheme.tertiary else Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
