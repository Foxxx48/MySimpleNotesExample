package com.fox.mysimplenotesexample.presentation.addnote

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fox.mysimplenotesexample.databinding.ActivityAddNoteBinding
import com.fox.mysimplenotesexample.domain.Note
import com.fox.mysimplenotesexample.presentation.main.MainActivity
import kotlin.properties.Delegates

class AddNoteActivity : AppCompatActivity() {
    private var _binding: ActivityAddNoteBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityAddNoteBinding = null")

    private val viewModel by lazy {
        ViewModelProvider(this).get(AddNoteViewModel::class.java)
    }

    var priority by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

                viewModel.addNote(note)
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