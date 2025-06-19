package com.example.safevault_compose.ui.screen.note

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.safevault_compose.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.safevault_compose.model.NoteModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Note(navController: NavHostController) {
    val context = LocalContext.current
    val database = FirebaseDatabase.getInstance()
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
    val notesRef = database.getReference("notes").child(uid)

    var searchQuery by remember { mutableStateOf("") }
    var allNotes by remember { mutableStateOf<List<NoteModel>>(emptyList()) }
    var selectedLabel by remember { mutableStateOf("") }

    // Load notes
    LaunchedEffect(Unit) {
        notesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val loadedNotes = snapshot.children.mapNotNull {
                    it.getValue(NoteModel::class.java)?.copy(id = it.key)
                }
                allNotes = loadedNotes
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Note", "Database error: ${error.message}")
            }
        })
    }

    val filteredNotes = allNotes.filter {
        (selectedLabel.isBlank() || it.label == selectedLabel) &&
                (searchQuery.isBlank() || it.title.contains(searchQuery, ignoreCase = true))
    }

    val labelOptions = allNotes.map { it.label }.distinct()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note") },
                actions = {
                    IconButton(onClick = { /* settings */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("note_detail") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {

            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                placeholder = { Text("Search note") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Label chips
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(labelOptions) { label ->
                    FilterChip(
                        selected = selectedLabel == label,
                        onClick = {
                            selectedLabel = if (selectedLabel == label) "" else label
                        },
                        label = { Text(label) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Notes list
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filteredNotes) { note ->
                    ListItem(
                        headlineContent = { Text(note.title) },
                        supportingContent = { Text(note.content.take(80)) },
                        leadingContent = {
                            Icon(
                                painter = painterResource(id = R.drawable.logo_safe_vault),
                                contentDescription = null,
                                modifier = Modifier.size(48.dp)
                            )
                        },
                        trailingContent = {
                            Row {
                                IconButton(onClick = {
                                    navController.navigate("note_detail?id=${note.id}")
                                }) {
                                    Icon(Icons.Default.MoreVert, contentDescription = "Edit")
                                }
                                IconButton(onClick = {
                                    note.id?.let { noteId ->
                                        notesRef.child(noteId).removeValue()
                                            .addOnSuccessListener {
                                                Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                                            }
                                            .addOnFailureListener {
                                                Toast.makeText(context, "Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                                            }
                                    }
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
