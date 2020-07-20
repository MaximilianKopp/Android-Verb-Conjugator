package com.eloquence.verbconjugator.model

import android.app.Application
import android.provider.Contacts
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.eloquence.verbconjugator.data.VerbDatabase
import com.eloquence.verbconjugator.data.VerbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class VerbViewModel(application: Application) : AndroidViewModel(application) {

    private val verbRepository: VerbRepository
    val allVerbs: LiveData<List<Verb>>
    val allWeakVerbs: LiveData<List<Verb>>
    val allStrongVerbs: LiveData<List<Verb>>
    val allReflexiveVerbs: LiveData<List<Verb>>
    val allSeparableVerbs: LiveData<List<Verb>>
    val allNonSeparableVerbs: LiveData<List<Verb>>
    val allFavourites: LiveData<List<Verb>>

    init {
        val verbDao = VerbDatabase.getDatabase(application.applicationContext).verbDao()
        verbRepository = VerbRepository(verbDao)
        allVerbs = verbRepository.allVerbs
        allWeakVerbs = verbRepository.allWeakVerbs
        allStrongVerbs = verbRepository.allStrongVerbs
        allReflexiveVerbs = verbRepository.allReflexiveVerbs
        allSeparableVerbs = verbRepository.allSeparableVerbs
        allNonSeparableVerbs = verbRepository.allNonSeparableVerbs
        allFavourites = verbRepository.allFavourites
    }

    fun isEmpty(): Int = verbRepository.isEmpty()


    fun insert(verb: Verb) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.insert(verb)
    }

    fun deleteFavourite(verbId: Int) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.deleteFavourite(verbId)
    }

    fun insertFavourite(favourite: Favourite) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.insertFavourite(favourite)
    }

    fun bulkInsert(verbs: List<Verb>) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.bulkInsert(verbs)
    }

    fun update(verb: Verb) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.update(verb)
    }

    fun migrateFavourite(verbId: Int) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.migrateFavourite(verbId)
    }

    fun getAllFilteredVerbs(constraint: String?) = verbRepository.getFilteredVerbs(constraint)

    fun getAllStoredFavourite() = verbRepository.allStoredFavourites

}