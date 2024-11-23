package com.example.uplift.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.logic.dao.GadquestionsDao

@Database(entities = [Gadquestions::class], version = 1, exportSchema = false)
abstract class GadDatabase : RoomDatabase() {

    abstract fun gadQuestionsDao(): GadquestionsDao

    companion object {
        @Volatile
        private var INSTANCE: GadDatabase? = null

        fun getDatabase(context: Context): GadDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GadDatabase::class.java,
                    "gadquestions_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
