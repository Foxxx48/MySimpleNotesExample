package com.fox.mysimplenotesexample.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.fox.mysimplenotesexample.data.AppDatabase
import com.fox.mysimplenotesexample.data.mapper.NoteMapper
import com.fox.mysimplenotesexample.domain.Note
import com.fox.mysimplenotesexample.domain.NotesRepository

class NotesRepositoryImpl(application: Application) :
    NotesRepository {

    private val notesDao =  AppDatabase.getInstance(application).notesDao()
    private val noteMapper = NoteMapper()

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
        notesDao.updateNote(noteMapper.mapEntityToDbModel(note))
    }

    override suspend fun getNote(noteId: Int): Note {
        val dbModel = notesDao.getNote(noteId)
        return noteMapper.mapDbModelToEntity(dbModel)
    }


}