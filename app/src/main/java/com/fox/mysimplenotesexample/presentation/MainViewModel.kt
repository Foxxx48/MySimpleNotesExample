package com.fox.mysimplenotesexample.presentation

import androidx.lifecycle.ViewModel
import com.fox.mysimplenotesexample.data.NotesDao
import com.fox.mysimplenotesexample.domain.AddNoteUseCase
import com.fox.mysimplenotesexample.domain.DeleteNoteUseCase
import com.fox.mysimplenotesexample.domain.GetNotesListUseCase

class MainViewModel(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNotesListUseCase: GetNotesListUseCase
) : ViewModel() {


}