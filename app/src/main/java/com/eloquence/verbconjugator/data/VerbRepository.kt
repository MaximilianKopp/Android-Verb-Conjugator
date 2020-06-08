package com.eloquence.verbconjugator.data

import androidx.lifecycle.LiveData
import com.eloquence.verbconjugator.model.Verb

class VerbRepository(private val verbDao: VerbDao) {

    val allVerbs: LiveData<List<Verb>> = verbDao.getAllVerbs()

    val allWeakVerbs: LiveData<List<Verb>> = verbDao.getAllWeakVerbs()

    val allStrongVerbs: LiveData<List<Verb>> = verbDao.getAllStrongVerbs()

    val allReflexiveVerbs: LiveData<List<Verb>> = verbDao.getAllReflexiveVerbs()

    val allSeparableVerbs: LiveData<List<Verb>> = verbDao.getAllSeparableVerbs()

    val allNonSeparableVerbs: LiveData<List<Verb>> = verbDao.getAllNonSeparableVerbs()

    fun getAllFilteredVerbs(constraint: String?): LiveData<List<Verb>> =
        verbDao.getAllFilteredVerbs(constraint)

    suspend fun insert(verb: Verb) = verbDao.insert(verb)

    suspend fun bulkInsert(verbs: List<Verb>) = verbDao.bulkInsert(verbs)

    suspend fun update(verb: Verb) = verbDao.update(verb)
}