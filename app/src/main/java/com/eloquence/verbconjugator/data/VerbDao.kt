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

    @Insert
    suspend fun bulkInsert(verbs: List<Verb>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(verb: Verb)
}