package com.example.safevault_compose.ui.screen.setting.fingerprint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting_Fingerprint_Submenu() {
    var fingerName by remember { mutableStateOf("Thumb") }

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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Fingerprint, // diganti
                        contentDescription = "Fingerprint Icon",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "FINGERPRINT DATA", // diganti
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Icon(
                    imageVector = Icons.Default.Fingerprint, // diganti
                    contentDescription = "Fingerprint Icon",
                    modifier = Modifier
                        .size(160.dp)
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = fingerName,
                    onValueChange = { fingerName = it },
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { /* TODO: Delete fingerprint data */ },
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
                        text = "Delete fingerprint data", // diganti
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    )
}