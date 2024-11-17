package com.example.uplift.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uplift.data.models.Mhiquestions
import com.example.uplift.logic.dao.MhiquestionsDao

@Database(entities = [Mhiquestions::class], version = 1, exportSchema = false)
abstract class MhiDatabase : RoomDatabase() {

    abstract fun mhiQuestionsDao(): MhiquestionsDao

    companion object {
        @Volatile
        private var INSTANCE: MhiDatabase? = null

        fun getDatabase(context: Context): MhiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MhiDatabase::class.java,
                    "mhi_questions_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

