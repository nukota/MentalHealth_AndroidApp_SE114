package com.example.uplift.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uplift.data.models.Diary
import com.example.uplift.logic.dao.DiaryDao

@Database(entities = [Diary::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    companion object{
        @Volatile
        private var instance: DiaryDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                DiaryDatabase::class.java,
                "diary_database"
            ).build()
    }
}
