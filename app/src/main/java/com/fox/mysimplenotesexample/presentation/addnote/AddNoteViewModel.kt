package com.fox.mysimplenotesexample.presentation.addnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fox.mysimplenotesexample.data.repository.NotesRepositoryImpl
import com.fox.mysimplenotesexample.domain.AddNoteUseCase
import com.fox.mysimplenotesexample.domain.Note
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application): AndroidViewModel(application) {
    private val notesRepository = NotesRepositoryImpl(application)
    private val addNoteUseCase = AddNoteUseCase(notesRepository)

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase(note)
        }
    }
}