package com.fox.mysimplenotesexample.domain

import com.fox.mysimplenotesexample.data.NoteDbModel

class GetNoteUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(noteId: Int): Note {
        return notesRepository.getNote(noteId)
    }
}