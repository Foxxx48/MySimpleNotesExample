package com.fox.mysimplenotesexample.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fox.mysimplenotesexample.data.NotesDao
import com.fox.mysimplenotesexample.data.repository.NotesRepositoryImpl
import com.fox.mysimplenotesexample.domain.AddNoteUseCase
import com.fox.mysimplenotesexample.domain.DeleteNoteUseCase
import com.fox.mysimplenotesexample.domain.GetNotesListUseCase
import com.fox.mysimplenotesexample.domain.Note
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val notesRepository = NotesRepositoryImpl(application)

    private val deleteNoteUseCase = DeleteNoteUseCase(notesRepository)
    private val getNotesListUseCase = GetNotesListUseCase(notesRepository)

    val notesList = getNotesListUseCase()

    fun deleteNote(note: Note) {
        viewModelScope.launch{
            deleteNoteUseCase(note)
        }
    }
}