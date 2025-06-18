package com.example.safevault_compose.ui.screen.auth

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safevault_compose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth_Calc_Combination() {
    var combination by remember { mutableStateOf("") }

    val buttonSize = 72.dp
    val calcButtons = listOf(
        listOf("C", "⌫", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "−"),
        listOf("1", "2", "3", "+"),
        listOf("🧮", "0", ".", "=")
    )
    TopAppBar(
        modifier = Modifier
            .padding(top = 5.dp),
        title = { Text("Label") },
        navigationIcon = {
            IconButton(onClick = { /* TODO: Back action */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Settings */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
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
        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_safe_vault_with_text),
            contentDescription = "Logo",
            modifier = Modifier
                .width(160.dp)
                .height(48.dp)
            ,
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Enter your calculator combination to unlock SafeVault",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(52.dp))

        // Display input
        OutlinedTextField(
            value = combination,
            onValueChange = { /* tidak ada perubahan karena ini hanya tampilan */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 10.dp, end = 10.dp)
            ,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onTertiary,
            ),
            readOnly = true,
            label = { Text("Combination") }, // opsional
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                disabledBorderColor = Color.LightGray
            )
        )


        Spacer(modifier = Modifier.height(24.dp))

        // Grid kalkulator
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
                                        // TODO: Validasi kombinasi
                                    }
                                    "🧮" -> {
                                        // TODO: Ganti ke scientific calculator
                                    }
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