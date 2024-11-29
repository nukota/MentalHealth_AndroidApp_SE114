package com.example.uplift.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uplift.data.models.Questions
import com.example.uplift.logic.dao.AnswersDao
import com.example.uplift.logic.dao.QuestionsDao

@Database(entities = [Questions::class], version = 1, exportSchema = false)
abstract class QuestionsDatabase : RoomDatabase() {

    abstract fun questionsDao(): QuestionsDao
    abstract fun answersDao(): AnswersDao

    companion object {
        @Volatile
        private var INSTANCE: QuestionsDatabase? = null

        fun getDatabase(context: Context): QuestionsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionsDatabase::class.java,
                    "questions_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
