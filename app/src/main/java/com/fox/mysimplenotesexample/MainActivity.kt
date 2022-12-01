package com.fox.mysimplenotesexample

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
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

    private lateinit var adapter: NotesAdapter

    private val notes = mutableListOf<Note>()

    private lateinit var dataBase: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = NotesDBHelper(this)
        dataBase = dbHelper.writableDatabase

        getData()


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
                removeItem(viewHolder.adapterPosition)
            }

        })

        itemTouchHelper.attachToRecyclerView(binding.rvNotes)
    }

    private fun removeItem(position: Int) {
        val id = notes[position].id
        val where = NotesContract._ID + " =?"
        val whereArgs = arrayOf(id.toString())
        dataBase.delete(NotesContract.TABLE_NAME, where, whereArgs)
        getData()
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("Range")
    private fun getData() {
        notes.clear()
        val cursor =
            dataBase.query(NotesContract.TABLE_NAME, null, null, null, null, null, NotesContract.COLUMN_DAY_OF_WEEK)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(NotesContract._ID))
            val title = cursor.getString(cursor.getColumnIndex(NotesContract.COLUMN_TITLE))
            val description =
                cursor.getString(cursor.getColumnIndex(NotesContract.COLUMN_DESCRIPTION))
            val dayOfWeek =
                cursor.getInt(cursor.getColumnIndex(NotesContract.COLUMN_DAY_OF_WEEK))
            val priority = cursor.getInt(cursor.getColumnIndex(NotesContract.COLUMN_PRIORITY))
            val note = Note(id, title, description, dayOfWeek, priority)
            notes.add(note)
        }
        cursor.close()
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