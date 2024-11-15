package com.maszhar.catatancepat.app

import android.app.Application
import androidx.room.Room
import com.maszhar.catatancepat.core.db.AppDatabase

class NoteApplication: Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "db_catatan"
        ).build()
    }
}