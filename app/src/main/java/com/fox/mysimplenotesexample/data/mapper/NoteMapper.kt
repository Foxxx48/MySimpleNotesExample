package com.fox.mysimplenotesexample.data.mapper

import com.fox.mysimplenotesexample.data.NoteDbModel
import com.fox.mysimplenotesexample.domain.Note

class NoteMapper {
    fun mapEntityToDbModel(note: Note): NoteDbModel {
        return NoteDbModel(
            id = note.id,
            title = note.title,
            description = note.description,
            dayOfWeek = note.dayOfWeek,
            priority = note.priority
        )
    }

    fun mapDbModelToEntity(noteDbModel: NoteDbModel): Note {
        return Note(
            id = noteDbModel.id,
            title = noteDbModel.title,
            description = noteDbModel.description,
            dayOfWeek = noteDbModel.dayOfWeek,
            priority = noteDbModel.priority
        )
    }

    fun mapListDbModelToListEntity(list: List<NoteDbModel>): List<Note> {
        return list.map {
            mapDbModelToEntity(it)
        }
    }
}