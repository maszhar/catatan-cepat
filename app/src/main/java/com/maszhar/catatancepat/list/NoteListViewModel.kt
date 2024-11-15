package com.maszhar.catatancepat.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maszhar.catatancepat.core.model.Note

class NoteListViewModel: ViewModel() {
    private val _notes = MutableLiveData<List<Note>>(listOf())
    val notes = _notes as LiveData<List<Note>>

    fun addNote(note: Note) {
        val newNotes = _notes.value?.toMutableList() ?: mutableListOf()
        newNotes.add(note)
        _notes.postValue(newNotes)
    }
}