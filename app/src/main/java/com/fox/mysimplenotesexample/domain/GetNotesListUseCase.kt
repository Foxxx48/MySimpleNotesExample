package com.fox.mysimplenotesexample.domain

class GetNotesListUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke() = notesRepository.getListNotes()
}