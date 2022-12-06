package com.fox.mysimplenotesexample.domain

class AddNoteUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(
        note: Note
    ) = notesRepository.addNote(note)
}