package com.maszhar.catatancepat.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maszhar.catatancepat.app.NoteApplication
import com.maszhar.catatancepat.core.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteEditorViewModel(app: Application) : AndroidViewModel(app) {
    private val db = (app as NoteApplication).db
    private var onSaved: ((Note) -> Unit)? = null

    val title = MutableLiveData("")
    val content = MutableLiveData("")

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            val titleSnapshot = title.value ?: ""
            val contentSnapshot = content.value ?: ""

            val note = Note(
                id = 0,
                title = titleSnapshot,
                content = contentSnapshot,
                order = 0
            )

            val noteDao = db.noteDao()
            noteDao.insert(note)

            onSaved?.invoke(note)
        }
    }

    fun setOnSavedListener(onSaved: (Note) -> Unit) {
        this.onSaved = onSaved
    }
}