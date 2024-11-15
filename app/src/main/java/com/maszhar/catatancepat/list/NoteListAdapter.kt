package com.maszhar.catatancepat.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.maszhar.catatancepat.R
import com.maszhar.catatancepat.core.model.Note
import com.maszhar.catatancepat.databinding.ItemNoteCardBinding

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteItemViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val binding: ItemNoteCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_note_card,
            parent,
            false
        )
        return NoteItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    inner class NoteItemViewHolder(private val binding: ItemNoteCardBinding) :
        ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.noteTitleText.text = note.title
            binding.noteContentText.text = note.content
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }
}