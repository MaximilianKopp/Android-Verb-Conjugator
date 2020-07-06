package com.eloquence.verbconjugator.conjugatorFragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.eloquence.verbconjugator.R
import com.eloquence.verbconjugator.model.Verb
import java.util.*

class InfoFragment : Fragment() {


    private lateinit var tvSubjFutureTwo: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_subj_tab, container, false)


        return view
    }
}