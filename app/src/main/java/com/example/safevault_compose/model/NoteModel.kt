// NoteModel.kt
package com.example.safevault_compose.model

data class NoteModel(
    val title: String = "",
    val content: String = "",
    val label: String = "",
    val id: String? = null // optional untuk navigasi dan edit
)
