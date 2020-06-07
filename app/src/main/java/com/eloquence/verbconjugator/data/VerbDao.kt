package com.eloquence.verbconjugator.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eloquence.verbconjugator.model.Verb

@Dao
interface VerbDao {

    @Query("SELECT * FROM verb_table ORDER BY infinitivePresent ASC LIMIT 500")
    fun getAllVerbs(): LiveData<List<Verb>>

    @Insert
    suspend fun insert(verb: Verb)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(verb: Verb)
}