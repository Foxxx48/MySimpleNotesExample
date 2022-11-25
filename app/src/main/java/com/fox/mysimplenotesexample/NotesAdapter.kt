package com.fox.mysimplenotesexample

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fox.mysimplenotesexample.databinding.NoteItemBinding
import com.google.android.material.color.MaterialColors.getColor

class NotesAdapter(var notesList: ArrayList<Note>) : RecyclerView.Adapter<NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        with(holder) {
            with(notesList[position]) {
                binding.tvNoteTitle.text = title
                binding.tvDescription.text = description
                binding.tvDayOfWeek.text = dayOfWeek
                determineTheImportance(holder, priority)
//                binding.root.setOnLongClickListener {
//                    if (holder != null) {
//                        Toast.makeText(holder.itemView.context, "$position", Toast.LENGTH_SHORT).show()
//                    }
//                    return@setOnLongClickListener true
//                }
               holder.itemView.setOnClickListener {
                   Toast.makeText(holder.itemView.context, "$position", Toast.LENGTH_SHORT).show()
               }
                holder.itemView.setOnLongClickListener {
                    if (holder != null) {
                        Toast.makeText(holder.itemView.context, "$position", Toast.LENGTH_SHORT)
                            .show()
                    }
                    return@setOnLongClickListener true
                }
            }
        }
    }

    fun determineTheImportance(holder: NotesViewHolder, priority: Int) {
        with(holder) {
            when (priority) {
                3 -> binding.tvNoteTitle.setBackgroundResource(R.color.not_important)
                2 -> binding.tvNoteTitle.setBackgroundResource(R.color.important)
                1 -> binding.tvNoteTitle.setBackgroundResource(R.color.highly_important)
                else -> {
                    binding.tvNoteTitle.setBackgroundResource(com.google.android.material.R.color.m3_ref_palette_white)
                }
            }
        }

    }


    fun myLog(message: Any?) {
        Log.d(TAG, "$message")
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    companion object {
        private const val TAG = "myApp"
    }
}