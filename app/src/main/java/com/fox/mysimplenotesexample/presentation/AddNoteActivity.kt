package com.fox.mysimplenotesexample.presentation

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fox.mysimplenotesexample.NotesContract
import com.fox.mysimplenotesexample.data.AppDatabase
import com.fox.mysimplenotesexample.data.Note
import com.fox.mysimplenotesexample.databinding.ActivityAddNoteBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import kotlin.properties.Delegates

class AddNoteActivity : AppCompatActivity() {
    private var _binding: ActivityAddNoteBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityAddNoteBinding = null")

    var priority by Delegates.notNull<Int>()

    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this.application)

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
                val note = Note(
                    title = title,
                    description = description,
                    dayOfWeek = dayOfWeek.toInt(),
                    priority = priority
                )
                lifecycleScope.launch {
                    database.notesDao().addNote(note)
                }
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