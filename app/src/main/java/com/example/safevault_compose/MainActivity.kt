package com.example.safevault_compose

import android.graphics.fonts.Font
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.safevault_compose.ui.theme.SafeVault_ComposeTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafeVault_ComposeTheme {
                Calc()
                }
            }
        }
    }

@Composable
fun Auth_Calc() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(90.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_safe_vault_with_text),
            contentDescription = "Logo",
            modifier = Modifier
                .width(164.dp)
                .height(49.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Sign in to your Account",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Enter your email and password to log in",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Field
        Text(text = "Email", color = Variables.Grey, fontSize = 15.sp)
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .background( Color(0xFFFFFFFF), RoundedCornerShape(10.dp))
                .border(1.dp, Variables.Stroke, RoundedCornerShape(10.dp)),
            placeholder = { Text("Enter email") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Password Field
        Text(text = "Password", color = Variables.Grey, fontSize = 15.sp)
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .background(Color.White, RoundedCornerShape(10.dp))
                .border(1.dp, Variables.Stroke, RoundedCornerShape(10.dp)),
            placeholder = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Forgot Password?",
            color = Color(0xFFEF7303),
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    // TODO: Tambahkan aksi, misalnya navigasi ke halaman reset password
                    println("Forgot Password clicked") // bisa diganti dengan navigasi
                }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Log In Button
        Button(
            //TODO: Tambahkan fungsi login
            onClick = { /* login logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF7303))
        ) {
            Text("Log In")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Or Divider
        Text("Or", color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(16.dp))

        // Google Button
        Button(
            //TODO: Tambahkan fungsi login Google
            onClick = { /* Google login */ },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(alpha = 0.2f, red = 0.5f, green = 0.5f, blue = 0.5f), RoundedCornerShape(10.dp))
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Google Logo",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            Text("Continue with Google", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Facebook Button
        Button(
            //TODO: Tambahkan fungsi login Facebook
            onClick = { /* Facebook login */ },
            modifier = Modifier
                .border(1.dp, Color(alpha = 0.2f, red = 0.5f, green = 0.5f, blue = 0.5f), RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_logo),
                contentDescription = "Facebook Logo",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            Text("Continue with Facebook", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(24.dp))

    }
}

@Composable
fun Auth_Calc_FaceID() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(90.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_safe_vault_with_text),
            contentDescription = "Logo",
            modifier = Modifier
                .width(57.dp)
                .height(57.dp)
                .align(Alignment.Start),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Position your face in the camera frame",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(111.dp))

        Image(
            painter = painterResource(id = R.drawable.face),
            contentDescription = "face id icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(136.dp)
                .height(136.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun Auth_Calc_FingerPrint() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(90.dp))

        // Logo
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
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(111.dp))

        Image(
            painter = painterResource(id = R.drawable.fingerprint),
            contentDescription = "face id icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(136.dp)
                .height(136.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

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
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 41.6.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(700),
                color = Variables.Black,
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Display input
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFFF0F0F0), RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = combination,
                fontSize = 20.sp,
                color = Color.Black
            )
        }

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
                                    color = if (isOperator) Color(0xFFEF7303) else Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Calc() {
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
                                    result = evaluateExpression(expression)
                                    if (result != "Error" && expression.isNotBlank()) {
                                        historyList.add(expression to result)
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


fun evaluateExpression(expression: String): String {
    try {
        val expr = expression
            .replace("−", "-")
            .replace("×", "*")
            .replace("÷", "/")
            .replace("%", "/100")

        val tokens = expr.toCharArray()
        val values = mutableListOf<Double>()
        val ops = mutableListOf<Char>()

        var i = 0
        while (i < tokens.size) {
            when {
                tokens[i].isWhitespace() -> i++

                tokens[i].isDigit() || tokens[i] == '.' -> {
                    val sb = StringBuilder()
                    while (i < tokens.size && (tokens[i].isDigit() || tokens[i] == '.')) {
                        sb.append(tokens[i++])
                    }
                    values.add(sb.toString().toDouble())
                }

                tokens[i] in listOf('+', '-', '*', '/') -> {
                    while (ops.isNotEmpty() && hasPrecedence(tokens[i], ops.last())) {
                        val op = ops.removeAt(ops.size - 1)
                        val b = values.removeAt(values.size - 1)
                        val a = values.removeAt(values.size - 1)
                        values.add(applyOp(op, b, a))
                    }
                    ops.add(tokens[i])
                    i++
                }

                else -> return "Error"
            }
        }

        while (ops.isNotEmpty()) {
            val op = ops.removeAt(ops.size - 1)
            val b = values.removeAt(values.size - 1)
            val a = values.removeAt(values.size - 1)
            values.add(applyOp(op, b, a))
        }

        val result = values.last()
        return if (result % 1.0 == 0.0) "%,d".format(result.toLong()) else "%,.4f".format(result)

    } catch (e: Exception) {
        return "Error"
    }
}

private fun hasPrecedence(op1: Char, op2: Char): Boolean {
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false
    return true
}

private fun applyOp(op: Char, b: Double, a: Double): Double {
    return when (op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> if (b == 0.0) throw ArithmeticException("Divide by zero") else a / b
        else -> 0.0
    }
}



object Variables {
    val Grey: Color = Color(0xFF6C7278)
    val Black: Color = Color(0xFF1A1C1E)
    val Stroke: Color = Color(0xFFEDF1F3)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SafeVault_ComposeTheme {
        Calc()
    }
}