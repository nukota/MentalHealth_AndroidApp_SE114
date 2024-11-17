package com.example.uplift.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uplift.data.models.Phqquestions
import com.example.uplift.logic.dao.MhiquestionsDao
import com.example.uplift.logic.dao.PhqquestionsDao

@Database(entities = [Phqquestions::class], version = 1, exportSchema = false)
abstract class PhqDatabase : RoomDatabase() {

    abstract fun phqQuestionsDao(): PhqquestionsDao

    companion object {
        @Volatile
        private var INSTANCE: PhqDatabase? = null

        fun getDatabase(context: Context): PhqDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhqDatabase::class.java,
                    "phq_questions_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
