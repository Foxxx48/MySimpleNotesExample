package com.fox.mysimplenotesexample.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fox.mysimplenotesexample.data.NoteDbModel
import com.fox.mysimplenotesexample.domain.Note

object NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}