package com.fox.mysimplenotesexample.domain

class EditNoteUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) {
         notesRepository.editNote(note)
    }
}