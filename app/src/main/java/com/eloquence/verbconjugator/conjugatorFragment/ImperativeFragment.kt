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

class ImperativeFragment : Fragment(), View.OnClickListener {

    private lateinit var tvImperative: TextView
    private lateinit var tts: TextToSpeech


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_imp_tab, container, false)
        val bundle = activity?.intent?.extras
        val verb: Verb? = bundle?.getParcelable("verb")

        tvImperative = view.findViewById(R.id.imperative)
        tvImperative.text = verb?.imperative

        val tvImperativeSpeaker = view.findViewById<TextView>(R.id.speaker_imperative)
        tvImperativeSpeaker.setOnClickListener(this)



        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.GERMAN)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported")
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        })

        return view
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.speaker_imperative) {
            speak(tvImperative.text)
        }
    }

    private fun speak(charSequence: CharSequence) {
        val text = charSequence.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}