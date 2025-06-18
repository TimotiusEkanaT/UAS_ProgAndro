package com.example.safevault_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.safevault_compose.ui.screen.calculator.Calc
import com.example.safevault_compose.ui.theme.SafeVault_ComposeTheme

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