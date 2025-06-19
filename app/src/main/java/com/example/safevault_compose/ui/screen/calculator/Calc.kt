package com.example.safevault_compose.ui.screen.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.safevault_compose.R
import com.example.safevault_compose.utils.evaluateExpression
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Calc(navController: NavHostController) {
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val historyList = remember { mutableStateListOf<Pair<String, String>>() }

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = Color(0xFFFF9800)
    )

    val numberButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = Color.Black
    )

    val buttons = listOf(
        listOf("C", "⌫", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "−"),
        listOf("1", "2", "3", "+"),
        listOf("🧮", "0", ".", "=")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {

        // History (terbalik dari bawah ke atas)
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            items(historyList.reversed()) { (exp, res) ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = exp,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "= $res",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }

        // Current expression and result
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = expression, fontSize = 28.sp)
            Text(text = if (result.isNotEmpty()) "= $result" else "", fontSize = 36.sp, fontWeight = FontWeight.Bold)
        }

        // Buttons
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    val isOrange = label in listOf("C", "⌫", "%", "÷", "×", "−", "+", "=")
                    val isIcon = label == "⌫" || label == "🧮"

                    Button(
                        onClick = {
                            when (label) {
                                "C" -> {
                                    expression = ""
                                    result = ""
                                }
                                "⌫" -> if (expression.isNotEmpty()) {
                                    expression = expression.dropLast(1)
                                }
                                "=" -> {
                                    val user = FirebaseAuth.getInstance().currentUser
                                    val uid = user?.uid

                                    if (uid != null) {
                                        val db = FirebaseDatabase.getInstance().getReference("calculator_combination").child(uid)
                                        db.get().addOnSuccessListener { snapshot ->
                                            val savedCombination = snapshot.child("combination").value?.toString()
                                            if (expression == savedCombination) {
                                                // ✅ Kombinasi cocok, arahkan ke halaman biometric
                                                navController.navigate("auth_biometric")
                                            } else {
                                                // ❌ Jika tidak cocok, lanjut hitung biasa
                                                result = evaluateExpression(expression)
                                                if (result != "Error" && expression.isNotBlank()) {
                                                    historyList.add(expression to result)
                                                }
                                            }
                                        }.addOnFailureListener {
                                            result = "Error"
                                        }
                                    } else {
                                        result = "Error"
                                    }
                                }

                                "🧮" -> {
                                    // Tambah aksi mode ilmiah di sini jika perlu
                                }
                                else -> expression += label
                            }
                        },
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        colors = if (isOrange) buttonColors else numberButtonColors,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        when {
                            label == "⌫" -> Image(
                                painter = painterResource(id = R.drawable.delete),
                                contentDescription = "Delete",
                                modifier = Modifier.size(24.dp)
                            )
                            label == "🧮" -> Image(
                                painter = painterResource(id = R.drawable.scientific),
                                contentDescription = "Scientific",
                                modifier = Modifier.size(24.dp)
                            )
                            else -> Text(text = label, fontSize = 24.sp)
                        }
                    }
                }
            }
        }
    }
}
