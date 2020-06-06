package com.eloquence.verbconjugator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eloquence.verbconjugator.model.Verb

@Database(entities = [Verb::class], version = 1, exportSchema = false)
abstract class VerbDatabase : RoomDatabase() {

    abstract fun verbDao(): VerbDao

    companion object {
        @Volatile
        var INSTANCE: VerbDatabase? = null

        fun getDatabase(context: Context): VerbDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VerbDatabase::class.java,
                    "verb_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}