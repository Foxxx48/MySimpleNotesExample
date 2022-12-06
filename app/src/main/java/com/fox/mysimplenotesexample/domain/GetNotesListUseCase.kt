package com.fox.mysimplenotesexample.domain

class GetNotesListUseCase(private val notesRepository: NotesRepository) {
    operator fun invoke() = notesRepository.getListNotes()
}