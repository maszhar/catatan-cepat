package com.maszhar.catatancepat.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maszhar.catatancepat.app.NoteApplication
import com.maszhar.catatancepat.core.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel(app: Application): AndroidViewModel(app) {
    private val db = (app as NoteApplication).db

    private val _notes = MutableLiveData<List<Note>>(listOf())
    val notes = _notes as LiveData<List<Note>>

    init {
        loadNotes()
    }

    fun addNote(note: Note) {
        val newNotes = _notes.value?.toMutableList() ?: mutableListOf()
        newNotes.add(note)
        _notes.postValue(newNotes)
    }

    fun loadNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val noteDao = db.noteDao()
            val newNotes = noteDao.getAll()
            _notes.postValue(newNotes)
        }
    }
}