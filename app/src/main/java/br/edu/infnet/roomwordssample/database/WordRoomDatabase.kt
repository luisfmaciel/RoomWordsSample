package br.edu.infnet.roomwordssample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.infnet.roomwordssample.dao.WordDao
import br.edu.infnet.roomwordssample.model.Word


@Database(
    entities = [Word::class],
    version = 1
)
abstract class WordRoomDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var instance: WordRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            WordRoomDatabase::class.java,
            "word_database")
            .build()
    }

}