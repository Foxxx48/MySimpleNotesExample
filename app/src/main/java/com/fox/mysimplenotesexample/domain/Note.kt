package com.fox.mysimplenotesexample.domain

data class Note(
    var id: Int = UNDEFINED_ID,
    val title: String,
    val description: String,
    val dayOfWeek: Int,
    val priority: Int,

) {
    companion object {
        const val UNDEFINED_ID = 0

    }
}
