package com.example.safevault_compose.ui.screen.setting.combination

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting_Calc_Combination(navController: NavHostController) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
    val dbRef = FirebaseDatabase.getInstance()
        .getReference("calculator_combination")
        .child(uid)

    var combinations by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }

    LaunchedEffect(Unit) {
        dbRef.get().addOnSuccessListener { snapshot ->
            val loaded = snapshot.children.mapNotNull {
                val id = it.key ?: return@mapNotNull null
                val value = it.child("value").getValue(String::class.java) ?: ""
                id to value
            }
            combinations = loaded
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Header
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Lock Icon",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "CALCULATOR COMBINATION",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // List combinations
            combinations.forEach { (id, value) ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("setting_combination_submenu?id=$id")
                    }
                    .padding(vertical = 12.dp)
                ) {
                    Text(
                        text = "Combination: $value",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "ID: $id",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Divider(modifier = Modifier.padding(top = 12.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Add new combination
            Text(
                text = "Add calculator combination",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("setting_combination_auth")
                    }
                    .padding(vertical = 16.dp)
            )
        }
    }
}
