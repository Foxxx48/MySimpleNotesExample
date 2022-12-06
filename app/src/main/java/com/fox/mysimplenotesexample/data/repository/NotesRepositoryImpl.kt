package com.fox.mysimplenotesexample.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.fox.mysimplenotesexample.data.NotesDao
import com.fox.mysimplenotesexample.data.mapper.NoteMapper
import com.fox.mysimplenotesexample.domain.Note
import com.fox.mysimplenotesexample.domain.NotesRepository

class NotesRepositoryImpl(private val notesDao: NotesDao, private val noteMapper: NoteMapper) :
    NotesRepository {
    override suspend fun addNote(note: Note) {
        notesDao.addNote(noteMapper.mapEntityToDbModel(note))
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note.id)
    }

    override fun getListNotes(): LiveData<List<Note>> = Transformations.map(
        notesDao.getNotesList()
    ) {
        noteMapper.mapListDbModelToListEntity(it)
    }

    override suspend fun editNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun getNote(noteId: Int): Note {
        TODO("Not yet implemented")
    }


}