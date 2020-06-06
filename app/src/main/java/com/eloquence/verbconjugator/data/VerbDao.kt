package com.eloquence.verbconjugator.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eloquence.verbconjugator.model.Verb

@Dao
interface VerbDao {

    @Query("SELECT * FROM verb_table")
    fun getAllVerbs(): LiveData<List<Verb>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(verb: Verb)
}