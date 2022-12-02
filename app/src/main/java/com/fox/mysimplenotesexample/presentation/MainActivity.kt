package com.fox.mysimplenotesexample.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.fox.mysimplenotesexample.data.AppDatabase
import com.fox.mysimplenotesexample.data.Note
import com.fox.mysimplenotesexample.databinding.ActivityMainBinding
import com.fox.mysimplenotesexample.presentation.adapter.NotesAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    private lateinit var adapter: NotesAdapter
    private lateinit var database: AppDatabase

    private var notes = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(application)

        lifecycleScope.launch(Dispatchers.IO) {
            getData()
        }
        adapter = NotesAdapter(notes)

        binding.rvNotes.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        binding.rvNotes.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        adapter.setOnMyCustomObjectListener(object : NotesAdapter.MyCustomObjectListener {
            override fun onObjectClick(position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    "Click at position: $position",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onObjectLongClick(position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    "Long click at position: $position",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                lifecycleScope.launch(Dispatchers.IO) {
                    removeItem(viewHolder.adapterPosition)
                }

            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvNotes)
    }


    private suspend fun removeItem(position: Int) {
        database.notesDao().deleteNote(position)
        getData()
    }

    private fun getData() {
        val notesFromDb = database.notesDao().getNotesList()
        notes.clear()
        notes.addAll(notesFromDb)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun myLog(message: Any?) {
        Log.d(TAG, "Main Activity: $message")
    }

    companion object {
        const val TAG = "myApp"
    }
}