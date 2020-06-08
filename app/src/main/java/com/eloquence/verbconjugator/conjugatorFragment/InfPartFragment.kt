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

class InfPartFragment : Fragment(), View.OnClickListener {

    private lateinit var tvInfinitiveOne: TextView
    private lateinit var tvInfinitiveTwo: TextView
    private lateinit var tvParticipleOne: TextView
    private lateinit var tvParticipleTwo: TextView

    private lateinit var tts: TextToSpeech

    private lateinit var verb: Verb

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_infpart_tab, container, false)

        val bundle = activity?.intent?.extras
        verb = bundle?.getParcelable("verb")!!

        val tvInfinitiveOneSpeaker =
            view.findViewById<TextView>(R.id.speaker_infinitive_one)
        tvInfinitiveOneSpeaker.setOnClickListener(this)
        tvInfinitiveOne = view.findViewById(R.id.infinitive_one)
        tvInfinitiveOne.text = verb.infinitivePresent

        val tvInfinitiveTwoSpeaker =
            view.findViewById<TextView>(R.id.speaker_infinitive_two)
        tvInfinitiveTwoSpeaker.setOnClickListener(this)
        tvInfinitiveTwo = view.findViewById(R.id.infinitive_two)
        tvInfinitiveTwo.text = verb.indicativePerfect

        val tvParticipleOneSpeaker =
            view.findViewById<TextView>(R.id.speaker_participle_one)
        tvParticipleOneSpeaker.setOnClickListener(this)
        tvParticipleOne = view.findViewById(R.id.participle_one)
        tvParticipleOne.text = verb.participleOne

        val tvParticipleTwoSpeaker =
            view.findViewById<TextView>(R.id.speaker_participle_two)
        tvParticipleTwoSpeaker.setOnClickListener(this)
        tvParticipleTwo = view.findViewById(R.id.participle_two)
        tvParticipleTwo.text = verb.participleTwo

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

    override fun onStop() {
        tts.stop()
        super.onStop()
    }

    override fun onDestroyView() {
        tts.shutdown()
        super.onDestroyView()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.speaker_infinitive_one -> speak(tvInfinitiveOne.text)
            R.id.speaker_infinitive_two -> speak(tvInfinitiveTwo.text)
            R.id.speaker_participle_one -> speak(tvParticipleOne.text)
            R.id.speaker_participle_two -> speak(tvParticipleTwo.text)
        }
    }

    private fun speak(charSequence: CharSequence) {
        val text = charSequence.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}