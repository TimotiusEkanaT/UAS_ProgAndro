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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafeVault_ComposeTheme {
                Auth_Calc()
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


object Variables {
    val Grey: Color = Color(0xFF6C7278)
    val Stroke: Color = Color(0xFFEDF1F3)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SafeVault_ComposeTheme {
        Auth_Calc_FaceID()
    }
}