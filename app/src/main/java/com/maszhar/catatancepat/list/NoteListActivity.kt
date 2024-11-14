package com.maszhar.catatancepat.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.maszhar.catatancepat.R
import com.maszhar.catatancepat.databinding.ActivityNoteListBinding
import com.maszhar.catatancepat.editor.NoteEditorActivity

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // get activity binding
        val binding: ActivityNoteListBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_list)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // configure on create button clicked
        binding.addNoteButton.setOnClickListener {
            // open note editor
            val intent = Intent(this, NoteEditorActivity::class.java)
            startActivity(intent)
        }
    }
}