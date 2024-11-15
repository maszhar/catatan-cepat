package com.maszhar.catatancepat.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maszhar.catatancepat.core.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<Note>

    @Insert
    suspend fun insert(note: Note)
}