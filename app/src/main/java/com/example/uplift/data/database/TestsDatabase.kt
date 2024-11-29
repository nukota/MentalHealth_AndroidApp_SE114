import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uplift.data.models.Tests
import com.example.uplift.logic.dao.TestsDao

@Database(entities = [Tests::class], version = 1, exportSchema = false)
abstract class TestsDatabase : RoomDatabase() {
    abstract fun testsDao(): TestsDao

    companion object {
        @Volatile
        private var INSTANCE: TestsDatabase? = null

        fun getDatabase(context: Context): TestsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TestsDatabase::class.java,
                    "tests_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
