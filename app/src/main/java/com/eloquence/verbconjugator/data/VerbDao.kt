package com.eloquence.verbconjugator.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eloquence.verbconjugator.model.Verb

@Dao
interface VerbDao {

    @Query("SELECT * FROM verb_table ORDER BY infinitivePresent ASC LIMIT 500")
    fun getAllVerbs(): LiveData<List<Verb>>

    @Query("SELECT * FROM verb_table WHERE verbclass = 'weak' LIMIT 500")
    fun getAllWeakVerbs(): LiveData<List<Verb>>

    @Query("SELECT * FROM verb_table WHERE verbclass = 'strong' LIMIT 500")
    fun getAllStrongVerbs(): LiveData<List<Verb>>

    @Query("SELECT * FROM verb_table WHERE reflexivity = 'reflexive' LIMIT 500")
    fun getAllReflexiveVerbs(): LiveData<List<Verb>>

    @Query("SELECT * FROM verb_table WHERE separability = 'separable' LIMIT 500")
    fun getAllSeparableVerbs(): LiveData<List<Verb>>

    @Query("SELECT * FROM verb_table WHERE separability = 'nonSeparable' LIMIT 500")
    fun getAllNonSeparableVerbs(): LiveData<List<Verb>>

    @Query("SELECT * FROM verb_table WHERE infinitivePresent LIKE '%' || :constraint || '%' Limit 10")
    fun getAllFilteredVerbs(constraint: String?): LiveData<List<Verb>>

    @Insert
    suspend fun insert(verb: Verb)

    @Insert
    suspend fun bulkInsert(verbs: List<Verb>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(verb: Verb)
}