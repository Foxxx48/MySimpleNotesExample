package com.fox.mysimplenotesexample.presentation.editnote

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fox.mysimplenotesexample.R
import com.fox.mysimplenotesexample.databinding.ActivityEditNoteBinding
import com.fox.mysimplenotesexample.databinding.ActivityMainBinding
import com.fox.mysimplenotesexample.domain.Note
import com.fox.mysimplenotesexample.presentation.addnote.AddNoteViewModel
import com.fox.mysimplenotesexample.presentation.main.MainActivity
import kotlin.properties.Delegates

class EditNoteActivity : AppCompatActivity() {

    private var _binding: ActivityEditNoteBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityEditNoteBinding = null")

    var noteItemId = 3

    private val viewModel by lazy {
        ViewModelProvider(this).get(EditNoteViewModel::class.java)
    }

    var priority = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        parseIntent()
        setPriority()

        viewModel.getNote(noteItemId)


        viewModel.noteItem.observe(this) {
            binding.etNoteTitle.setText(it.title)
            binding.etDescription.setText(it.description)

        }

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etNoteTitle.text.toString().trim()

            val description = binding.etDescription.text.toString().trim()

            val dayOfWeek = binding.spinnerDayOfWeek.selectedItemId

            if (isField(title, description)) {
                val note = Note(
                    id = noteItemId,
                    title = title,
                    description = description,
                    dayOfWeek = dayOfWeek.toInt(),
                    priority = priority
                )

                viewModel.editNote(note)
                myLog(note.priority)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Error! Fields must be not empty", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun parseIntent() {
        if (!intent.hasExtra(EXTRA_NOTE_ITEM_ID)) {
            throw RuntimeException("Param noteId is absent")
        }
        noteItemId = intent.getIntExtra(EXTRA_NOTE_ITEM_ID, 100500)
    }

    private fun setPriority() {
        binding.rbPriority1.setOnClickListener {
            priority = 1
        }
        binding.rbPriority2.setOnClickListener {
            priority = 2
        }
        binding.rbPriority3.setOnClickListener {
            priority = 3
        }
    }

    private fun isField(title: String, description: String): Boolean {
        return title.isNotEmpty() && description.isNotEmpty()
    }

    fun myLog(message: Any?) {
        Log.d(MainActivity.TAG, "Edit Note Activity: $message")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val EXTRA_NOTE_ITEM_ID = "extra_note_item_id"

        fun newEditInstance(context: Context, noteId: Int): Intent {
            val intent = Intent(context, EditNoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE_ITEM_ID, noteId)
            return intent
        }
    }
}