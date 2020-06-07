package com.eloquence.verbconjugator.data

import androidx.lifecycle.LiveData
import com.eloquence.verbconjugator.model.Verb

class VerbRepository(private val verbDao: VerbDao) {

    val allVerbs: LiveData<List<Verb>> = verbDao.getAllVerbs()

    suspend fun insert(verb: Verb) = verbDao.insert(verb)

    suspend fun bulkInsert(verbs: List<Verb>) = verbDao.bulkInsert(verbs)

    suspend fun update(verb: Verb) = verbDao.update(verb)
}