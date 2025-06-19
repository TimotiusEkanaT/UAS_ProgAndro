// Notes_Detail.kt
package com.example.safevault_compose.ui.screen.note

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.safevault_compose.model.NoteModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notes_Detail(navController: NavHostController, noteId: String? = null) {
    val context = LocalContext.current
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var label by remember { mutableStateOf("General") }

    val noteRef = FirebaseDatabase.getInstance().getReference("notes").child(uid)

    // If editing existing note
    LaunchedEffect(noteId) {
        if (noteId != null) {
            noteRef.child(noteId).get().addOnSuccessListener { snapshot ->
                val note = snapshot.getValue(NoteModel::class.java)
                if (note != null) {
                    title = note.title
                    content = note.content
                    label = note.label
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId != null) "Edit Note" else "New Note") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    val note = NoteModel(title, content, label)
                    val targetRef = if (noteId != null) noteRef.child(noteId) else noteRef.push()
                    targetRef.setValue(note).addOnSuccessListener {
                        Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }) {
                    Text("Save")
                }

                OutlinedButton(onClick = { navController.popBackStack() }) {
                    Text("Cancel")
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize(),
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = label,
                onValueChange = { label = it },
                label = { Text("Label") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
