package com.fox.mysimplenotesexample.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes" )
    fun getNotesList(): List<Note>

    @Insert(entity = Note::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update(onConflict = REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM notes WHERE id=:id")
    suspend fun deleteNote(id: Int)

    @Query("SELECT * FROM notes WHERE id=:id LIMIT 1")
    suspend fun getNote(id: Int): Note
}