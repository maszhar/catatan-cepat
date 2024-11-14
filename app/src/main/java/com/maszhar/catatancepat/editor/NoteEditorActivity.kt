package com.maszhar.catatancepat.editor

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.maszhar.catatancepat.R
import com.maszhar.catatancepat.databinding.ActivityNoteEditorBinding

class NoteEditorActivity : AppCompatActivity() {
    private lateinit var vm: NoteEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // layout binding
        val binding: ActivityNoteEditorBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_editor)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // set toolbar as action bar of activity
        setSupportActionBar(binding.toolbar)

        // get view model
        vm = ViewModelProvider(this)[NoteEditorViewModel::class.java]

        // set vm on layout
        binding.vm = vm
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note_editor, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.save_menu) {
            val intent = Intent()
            intent.putExtra(RESULT_TITLE_KEY, vm.title.value ?: "")
            intent.putExtra(RESULT_CONTENT_KEY, vm.content.value ?: "")

            setResult(RESULT_OK, intent)
            return true
        }
        return false
    }

    companion object {
        const val RESULT_TITLE_KEY = "title"
        const val RESULT_CONTENT_KEY = "content"
    }
}