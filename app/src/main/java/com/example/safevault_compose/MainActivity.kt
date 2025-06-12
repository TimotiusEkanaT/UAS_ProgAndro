package com.example.safevault_compose

import android.graphics.fonts.Font
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.safevault_compose.ui.theme.SafeVault_ComposeTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Divider
import kotlin.math.round


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafeVault_ComposeTheme(dynamicColor = false) {
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
                .align(Alignment.Start),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Sign in to your Account",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 41.6.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Enter your email and password to log in",
            fontSize = 15.sp,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Field
        Text(text = "Email",
            color = Variables.Grey,
            fontSize = 15.sp,
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .background( Color(0xFFFFFFFF), RoundedCornerShape(10.dp))
                .border(1.dp, Variables.Stroke, RoundedCornerShape(10.dp)),
            placeholder = { Text("Enter email") },
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Password Field
        Text(text = "Password", color = Variables.Grey, fontSize = 15.sp,style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
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
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
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
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Log In")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Or Divider
        Text("Or", color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(16.dp))

        // Google Button
        OutlinedButton(
            //TODO: Tambahkan fungsi login Google
            onClick = { /* Google login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
            ,
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
        OutlinedButton(
            //TODO: Tambahkan fungsi login Facebook
            onClick = { /* Facebook login */ },
            modifier = Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth_Calc_FaceID() {
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
            text = "Position your face in the camera frame",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth_Calc_FingerPrint() {
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
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notes() {
    var searchQuery by remember { mutableStateOf("") }
    val labels = listOf("Label", "Label", "Label", "Label", "Label", "Label")
    var selectedLabel by remember { mutableStateOf(labels[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                placeholder = { Text("Search note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, RoundedCornerShape(30.dp))
                    .background(Color(0xFFFFFFFF), RoundedCornerShape(30.dp))
                    .border(1.dp, Variables.Stroke, RoundedCornerShape(30.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.onTertiary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Chips Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(labels) { label ->
                    FilterChip(
                        selected = selectedLabel == label,
                        onClick = { selectedLabel = label },
                        label = { Text(label) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // List of Items
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(10) {
                    ListItem(
                        headlineContent = { Text("List item") },
                        supportingContent = {
                            Text("Supporting line text lorem ipsum dolor sit amet, consectetur.",
                                modifier = Modifier
                                    .width(268.dp)
                                    .height(40.dp))
                        },
                        leadingContent = {
                            Image(
                                painter = painterResource(id = R.drawable.image_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                            )
                        },
                        trailingContent = {
                            IconButton(onClick = { /* TODO */ }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun Notes_Detail() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var noteText by remember { mutableStateOf("") }
    var showFabMenu by remember { mutableStateOf(false) }

    val fabItems = listOf(
        "Voice Note" to Icons.Default.Mic,
        "Photo" to Icons.Default.Photo,
        "Checkboxes" to Icons.Default.CheckBox
    )

    val keyboardVisible = WindowInsets.isImeVisible

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                navigationIcon = {
                    IconButton(onClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {
                AnimatedVisibility(visible = showFabMenu) {
                    Column {
                        fabItems.forEach { (label, icon) ->
                            ExtendedFloatingActionButton(
                                text = { Text(label) },
                                icon = { Icon(icon, contentDescription = label) },
                                onClick = { /* TODO: Handle action */ },
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }

                FloatingActionButton(
                    onClick = { showFabMenu = !showFabMenu },
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(
                        imageVector = if (showFabMenu) Icons.Default.Close else Icons.Default.AttachFile,
                        contentDescription = "Menu"
                    )
                }
            }
        },
        bottomBar = {
            if (!keyboardVisible) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { /* Save logic */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text("Save")
                    }

                    OutlinedButton(
                        onClick = { /* Cancel logic */ },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                TextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    placeholder = { Text("Start writing your note...") },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .weight(1f),
                    singleLine = false,
//                    shape = RoundedCornerShape(16.dp),
                    maxLines = Int.MAX_VALUE
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun Setting() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Back action */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "SECURITY SETTINGS",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SecurityOption("FaceID", Icons.Default.Face)
                    SecurityOption("Fingerprint", Icons.Default.Fingerprint)
                    SecurityOption("Calculator Combination", Icons.Default.Lock)
                }

            }
        }
    )
}

@Composable
fun SecurityOption(label: String, icon: ImageVector) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.tertiary,
        tonalElevation = 4.dp,
        modifier = Modifier
            .widthIn(min = 150.dp)
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable { /* TODO: Action */ }
                .padding(horizontal = 12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = label, color = MaterialTheme.colorScheme.secondary)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting_FaceID() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Back action */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },

        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                // Label + Icon
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Face ID Icon",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "FACE DATA",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Face list
                FaceDataItem("Face 1")
                FaceDataItem("Face 2")

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = "Add face data",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* TODO: Add new face data */ }
                        .padding(vertical = 16.dp)
                )
            }
        }
    )
}

@Composable
fun FaceDataItem(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate or show details */ }
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "More"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting_FaceID_Submenu() {
    var faceName by remember { mutableStateOf("Face 1") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Back */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                // Header label
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Face, // Ganti jika punya ikon khusus
                        contentDescription = "Face ID Icon",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "FACE DATA",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Icon wajah besar
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "User Face Icon",
                    modifier = Modifier
                        .size(160.dp)
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Field nama face
                OutlinedTextField(
                    value = faceName,
                    onValueChange = { faceName = it },
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)

                )

                Spacer(modifier = Modifier.weight(1f))

                // Tombol hapus
                Button(
                    onClick = { /* TODO: Delete face data */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Delete face data",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

            }
        }
    )
}


object Variables {
    val Grey: Color = Color(0xFF6C7278)
    val Black: Color = Color(0xFF1A1C1E)
    val Stroke: Color = Color(0xFFEDF1F3)
    val StaticTitleLargeSize = 22.sp
    val StaticTitleLargeLineHeight = 28.sp
    val SchemesOnSurface: Color = Color(0xFF1D1B20)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SafeVault_ComposeTheme(dynamicColor = false) {
        Setting_FaceID_Submenu()
    }
}