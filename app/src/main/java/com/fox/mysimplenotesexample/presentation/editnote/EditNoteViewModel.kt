package com.fox.mysimplenotesexample.presentation.editnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fox.mysimplenotesexample.data.repository.NotesRepositoryImpl
import com.fox.mysimplenotesexample.domain.EditNoteUseCase
import com.fox.mysimplenotesexample.domain.GetNoteUseCase
import com.fox.mysimplenotesexample.domain.Note
import kotlinx.coroutines.launch

class EditNoteViewModel(application: Application) : AndroidViewModel(application) {
    private val notesRepository = NotesRepositoryImpl(application)
    private val editNoteUseCase = EditNoteUseCase(notesRepository)
    private val getNoteUseCase = GetNoteUseCase(notesRepository)

    private val _noteItem = MutableLiveData<Note>()
    val noteItem: LiveData<Note>
        get() = _noteItem

    fun editNote(note: Note) {
        viewModelScope.launch {
            editNoteUseCase(note)
        }
    }

    fun getNote(noteId: Int) {
        viewModelScope.launch {
            val item = getNoteUseCase(noteId)
            _noteItem.value = item
        }
    }
}