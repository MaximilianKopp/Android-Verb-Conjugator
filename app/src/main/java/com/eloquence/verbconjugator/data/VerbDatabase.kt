package com.eloquence.verbconjugator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eloquence.verbconjugator.model.Favourite
import com.eloquence.verbconjugator.model.Verb

@Database(entities = [Verb::class, Favourite::class], version = 1, exportSchema = false)
abstract class VerbDatabase : RoomDatabase() {

    abstract fun verbDao(): VerbDao

    companion object {
        @Volatile
        var INSTANCE: VerbDatabase? = null

        val MIGRATION1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM verb_table")
                database.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='verb_table'")
            }
        }

        fun getDatabase(context: Context): VerbDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VerbDatabase::class.java,
                    "verb_database"
                )
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION1_2)
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}