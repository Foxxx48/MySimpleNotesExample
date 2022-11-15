package com.fox.mysimplenotesexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.fox.mysimplenotesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notes.add(Note("Парикмахер", "Сделать прическу", "Понедельник", 2))
        notes.add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3))
        notes.add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3))
        notes.add(Note("Стоматолог", "Вылечить зубы", "Понедельник", 2))
        notes.add(Note("Парикмахер", "Сделать прическу к выпускному", "Среда", 1))
        notes.add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3))
        notes.add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3))
        val adapter = NotesAdapter(notes)
        binding.rvNotes.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        binding.rvNotes.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        var notes = ArrayList<Note>()
    }
}