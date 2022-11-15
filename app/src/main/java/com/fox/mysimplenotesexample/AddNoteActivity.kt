package com.fox.mysimplenotesexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import com.fox.mysimplenotesexample.databinding.ActivityAddNoteBinding
import kotlin.properties.Delegates

class AddNoteActivity : AppCompatActivity() {
    private var _binding: ActivityAddNoteBinding? = null
    private val binding get() = _binding?: throw RuntimeException("ActivityAddNoteBinding = null")

    var priority by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = binding.etNoteTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val dayOfWeek = binding.spinnerDayOfWeek.selectedItem.toString()
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
            val note = Note(title, description, dayOfWeek, priority )
            MainActivity.notes.add(note)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}