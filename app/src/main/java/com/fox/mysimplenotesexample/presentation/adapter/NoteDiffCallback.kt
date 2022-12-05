package com.fox.mysimplenotesexample.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fox.mysimplenotesexample.data.NoteDbModel

object NoteDiffCallback : DiffUtil.ItemCallback<NoteDbModel>() {
    override fun areItemsTheSame(oldItem: NoteDbModel, newItem: NoteDbModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteDbModel, newItem: NoteDbModel): Boolean {
        return oldItem == newItem
    }
}