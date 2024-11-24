import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uplift.data.models.Specialists
import com.example.uplift.logic.dao.SpecialistsDao

@Database(entities = [Specialists::class], version = 1, exportSchema = false)
abstract class SpecialistsDatabase : RoomDatabase() {
    abstract fun specialistsDao(): SpecialistsDao

    companion object {
        @Volatile
        private var INSTANCE: SpecialistsDatabase? = null

        fun getDatabase(context: Context): SpecialistsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpecialistsDatabase::class.java,
                    "specialists_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
