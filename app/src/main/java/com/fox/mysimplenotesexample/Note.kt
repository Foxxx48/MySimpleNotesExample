package com.fox.mysimplenotesexample

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val dayOfWeek: Int,
    val priority: Int,
)
