package com.fox.mysimplenotesexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.fox.mysimplenotesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    val adapter = NotesAdapter(notes)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (notes.isEmpty()) {
            notes.add(Note("Парикмахер", "Сделать прическу", "Понедельник", 2))
            notes.add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3))
            notes.add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3))
            notes.add(Note("Стоматолог", "Вылечить зубы", "Понедельник", 2))
            notes.add(Note("Парикмахер", "Сделать прическу к выпускному", "Среда", 1))
            notes.add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3))
            notes.add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3))
        }


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
                removeItem(viewHolder.adapterPosition)
            }

        })

        itemTouchHelper.attachToRecyclerView(binding.rvNotes)
    }

    private fun removeItem(position: Int) {
        notes.removeAt(position)
        adapter.notifyDataSetChanged()
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
        val notes = arrayListOf<Note>()
    }
}