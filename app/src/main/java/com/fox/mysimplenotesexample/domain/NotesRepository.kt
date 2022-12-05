package com.fox.mysimplenotesexample.domain

import androidx.lifecycle.LiveData


interface NotesRepository {
    suspend fun addNote(note: Note)

    suspend fun deleteNote(note:Note)

    suspend fun editNote(note:Note)

    suspend fun getNote(noteId: Int): Note

    fun getListNotes(): LiveData<List<Note>>
}