package com.fox.mysimplenotesexample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fox.mysimplenotesexample.R
import com.fox.mysimplenotesexample.databinding.ActivityEditNoteBinding
import com.fox.mysimplenotesexample.databinding.ActivityMainBinding

class EditNoteActivity : AppCompatActivity() {

    private var _binding: ActivityEditNoteBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityEditNoteBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}