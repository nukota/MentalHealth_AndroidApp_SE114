import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uplift.data.models.Testresults
import com.example.uplift.logic.dao.TestresultsDao

@Database(entities = [Testresults::class], version = 1, exportSchema = false)
abstract class TestresultsDatabase : RoomDatabase() {
    abstract fun testresultsDao(): TestresultsDao

    companion object {
        @Volatile
        private var INSTANCE: TestresultsDatabase? = null

        fun getDatabase(context: Context): TestresultsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TestresultsDatabase::class.java,
                    "testresults_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
