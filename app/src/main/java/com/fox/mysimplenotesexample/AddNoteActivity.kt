package com.fox.mysimplenotesexample

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fox.mysimplenotesexample.databinding.ActivityAddNoteBinding
import kotlin.properties.Delegates

class AddNoteActivity : AppCompatActivity() {
    private var _binding: ActivityAddNoteBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityAddNoteBinding = null")

    var priority by Delegates.notNull<Int>()

    lateinit var dbHelper: NotesDBHelper
    lateinit var dataBase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = NotesDBHelper(this)

        dataBase = dbHelper.writableDatabase

        binding.rbPriority1.setOnClickListener {
            priority = 1
        }

        binding.rbPriority2.setOnClickListener {
            priority = 2
        }

        binding.rbPriority3.setOnClickListener {
            priority = 3
        }


        binding.btnSaveNote.setOnClickListener {
            val title = binding.etNoteTitle.text.toString().trim()

            val description = binding.etDescription.text.toString().trim()

            val dayOfWeek = binding.spinnerDayOfWeek.selectedItemId

            if (isField(title, description)) {
                val contentValues = ContentValues()
                contentValues.put(NotesContract.COLUMN_TITLE, title)
                contentValues.put(NotesContract.COLUMN_DESCRIPTION, description)
                contentValues.put(NotesContract.COLUMN_DAY_OF_WEEK, dayOfWeek)
                contentValues.put(NotesContract.COLUMN_PRIORITY, priority)

                dataBase.insert(NotesContract.TABLE_NAME, null, contentValues)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
//            onBackPressed()
            } else {
                Toast.makeText(this, "Error! Fields must be not empty", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun isField(title: String, description: String): Boolean {
        return title.isNotEmpty() && description.isNotEmpty()
    }

    fun myLog(message: Any?) {
        Log.d(MainActivity.TAG, "Add Note Activity: $message")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}