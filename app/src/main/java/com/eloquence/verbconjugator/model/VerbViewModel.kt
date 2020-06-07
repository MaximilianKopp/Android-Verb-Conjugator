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

    init {
        val verbDao = VerbDatabase.getDatabase(application.applicationContext).verbDao()
        verbRepository = VerbRepository(verbDao)
        allVerbs = verbRepository.allVerbs
    }

    fun insert(verb: Verb) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.insert(verb)
    }

    fun update(verb: Verb) = viewModelScope.launch(Dispatchers.IO) {
        verbRepository.update(verb)
    }

}