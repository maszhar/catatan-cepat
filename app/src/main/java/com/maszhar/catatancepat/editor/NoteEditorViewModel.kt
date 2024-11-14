package com.maszhar.catatancepat.editor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteEditorViewModel: ViewModel() {
    val title = MutableLiveData("")
    val content = MutableLiveData("")
}