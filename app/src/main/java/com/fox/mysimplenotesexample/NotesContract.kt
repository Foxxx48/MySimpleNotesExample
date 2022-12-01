package com.fox.mysimplenotesexample

import android.provider.BaseColumns
import android.provider.BaseColumns._ID


class NotesContract {
    companion object {
        const val TABLE_NAME = "notes"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DAY_OF_WEEK = "day_of_week"
        const val COLUMN_PRIORITY = "priority"
        const val TYPE_TEXT = "TEXT"
        const val TYPE_INTEGER = "INT"
        const val _ID = BaseColumns._ID

        const val CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE +
                " " + TYPE_TEXT + ", " + COLUMN_DESCRIPTION + " " + TYPE_TEXT + ", " + COLUMN_DAY_OF_WEEK +
                " " + TYPE_INTEGER + ", " + COLUMN_PRIORITY + " " + TYPE_INTEGER + ")"

        const val DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME
    }


}