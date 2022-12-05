package com.fox.mysimplenotesexample.domain

class DeleteNoteUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(
        note: Note
    ) = notesRepository.deleteNote(note)
}