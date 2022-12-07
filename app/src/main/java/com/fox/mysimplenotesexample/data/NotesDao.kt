package com.fox.mysimplenotesexample.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes" )
    fun getNotesList(): LiveData<List<NoteDbModel>>

    @Insert(entity = NoteDbModel::class, onConflict = REPLACE)
    suspend fun addNote(note: NoteDbModel)

    @Query("DELETE FROM notes WHERE id=:noteId")
    suspend fun deleteNote(noteId: Int)

    @Query("SELECT * FROM notes WHERE id=:noteId LIMIT 1")
    suspend fun getNote(noteId: Int): NoteDbModel

    @Update(onConflict = REPLACE)
    suspend fun updateNote(note: NoteDbModel)
}