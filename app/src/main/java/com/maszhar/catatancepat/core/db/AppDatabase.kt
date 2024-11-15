package com.maszhar.catatancepat.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maszhar.catatancepat.core.dao.NoteDao
import com.maszhar.catatancepat.core.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}