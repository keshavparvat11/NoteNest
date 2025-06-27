package com.example.notenest.modle

data class Note(
    var id: String = "",
    val title: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)