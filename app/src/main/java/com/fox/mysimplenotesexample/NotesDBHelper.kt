package com.fox.mysimplenotesexample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(NotesContract.CREATE_COMMAND)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(NotesContract.DROP_COMMAND)
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "notes.db"
        private const val DB_VERSION = 1
    }
}