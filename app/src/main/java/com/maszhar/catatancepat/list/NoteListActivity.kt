package com.maszhar.catatancepat.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maszhar.catatancepat.R
import com.maszhar.catatancepat.core.model.Note
import com.maszhar.catatancepat.databinding.ActivityNoteListBinding
import com.maszhar.catatancepat.editor.NoteEditorActivity

class NoteListActivity : AppCompatActivity() {
    private lateinit var vm: NoteListViewModel

    private val noteEditorLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), object: ActivityResultCallback<ActivityResult> {
        override fun onActivityResult(result: ActivityResult) {
            if(result.resultCode == RESULT_OK) {
                val intent = result.data ?: return
                val title = intent.getStringExtra(NoteEditorActivity.RESULT_TITLE_KEY) ?: ""
                val content = intent.getStringExtra(NoteEditorActivity.RESULT_CONTENT_KEY) ?: ""

                val note = Note(
                    0,
                    title,
                    content,
                    0
                )

                vm.addNote(note)
            }
        }
    })

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

        // get view model
        vm = ViewModelProvider(this)[NoteListViewModel::class.java]

        // configure on create button clicked
        binding.addNoteButton.setOnClickListener {
            // open note editor
            val intent = Intent(this, NoteEditorActivity::class.java)
            noteEditorLauncher.launch(intent)
        }

        // configure note list
        binding.noteList.layoutManager = LinearLayoutManager(this)
        val noteListAdapter = NoteListAdapter()
        binding.noteList.adapter = noteListAdapter

        // listen notes data
        vm.notes.observe(this) { notes ->
            noteListAdapter.submitList(notes)
        }
    }
}