package com.eloquence.verbconjugator.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.eloquence.verbconjugator.data.VerbDatabase
import com.eloquence.verbconjugator.data.VerbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerbViewModel(application: Application) : AndroidViewModel(application) {

    private val verbRepository: VerbRepository
    val allVerbs: LiveData<List<Verb>>
    val allWeakVerbs: LiveData<List<Verb>>
    val allStrongVerbs: LiveData<List<Verb>>
    val allReflexiveVerbs: LiveData<List<Verb>>
    val allSeparableVerbs: LiveData<List<Verb>>
    val allNonSeparableVerbs: LiveData<List<Verb>>

    init {
        val verbDao = VerbDatabase.getDatabase(application.applicationContext).verbDao()
        verbRepository = VerbRepository(verbDao)
        allVerbs = verbRepository.allVerbs
        allWeakVerbs = verbRepository.allWeakVerbs
        allStrongVerbs = verbRepository.allStrongVerbs
        allReflexiveVerbs = verbRepository.allReflexiveVerbs
        allSeparableVerbs = verbRepository.allSeparableVerbs
        allNonSeparableVerbs = verbRepository.allNonSeparableVerbs
    }

    fun insert(verb: Verb) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.insert(verb)
    }

    fun bulkInsert(verbs: List<Verb>) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.bulkInsert(verbs)
    }

    fun update(verb: Verb) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.update(verb)
    }

    fun getAllFilteredVerbs(constraint: String?) = verbRepository.getAllFilteredVerbs(constraint)

}