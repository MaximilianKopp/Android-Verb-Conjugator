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

class SubjunctiveFragment : Fragment(), View.OnClickListener {

    private lateinit var tvSubjOne: TextView
    private lateinit var tvSubjTwo: TextView
    private lateinit var tvSubjPerfect: TextView
    private lateinit var tvSubjPluPerfect: TextView
    private lateinit var tvSubjFutureOne: TextView
    private lateinit var tvSubjFutureTwo: TextView

    private lateinit var tts: TextToSpeech

    private lateinit var verb: Verb

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_subj_tab, container, false)

        val bundle = activity?.intent?.extras
        verb = bundle?.getParcelable("verb")!!

        val tvSubjOneSpeaker = view.findViewById<TextView>(R.id.speaker_subj_one)
        tvSubjOneSpeaker.setOnClickListener(this)
        tvSubjOne = view.findViewById(R.id.subj_one)
        tvSubjOne.text = verb.subjunctiveOne

        val tvSubjTwoSpeaker = view.findViewById<TextView>(R.id.speaker_subj_two)
        tvSubjTwoSpeaker.setOnClickListener(this)
        tvSubjTwo = view.findViewById(R.id.subj_two)
        tvSubjTwo.text = verb.subjunctiveTwo

        val tvSubjPerfectSpeaker = view.findViewById<TextView>(R.id.speaker_subj_perfect)
        tvSubjPerfectSpeaker.setOnClickListener(this)
        tvSubjPerfect = view.findViewById(R.id.subj_perfect)
        tvSubjPerfect.text = verb.subjunctivePerfect

        val tvSubjPluPerfectSpeaker =
            view.findViewById<TextView>(R.id.speaker_subj_pluperfect)
        tvSubjPluPerfectSpeaker.setOnClickListener(this)
        tvSubjPluPerfect = view.findViewById(R.id.subj_pluperfect)
        tvSubjPluPerfect.text = (verb.subjunctivePluPerfect)

        val tvSubjFutureOneSpeaker =
            view.findViewById<TextView>(R.id.speaker_subj_future_one)
        tvSubjFutureOneSpeaker.setOnClickListener(this)
        tvSubjFutureOne = view.findViewById(R.id.subj_future_one)
        tvSubjFutureOne.text = verb.subjunctiveFutureOne

        val tvSubjFutureTwoSpeaker =
            view.findViewById<TextView>(R.id.speaker_subj_future_two)
        tvSubjFutureTwoSpeaker.setOnClickListener(this)
        tvSubjFutureTwo = view.findViewById(R.id.subj_future_two)
        tvSubjFutureTwo.text = verb.subjunctiveFutureTwo

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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.speaker_subj_one -> speak(tvSubjOne.text)
            R.id.speaker_subj_two -> speak(tvSubjTwo.text)
            R.id.speaker_subj_perfect -> speak(tvSubjPerfect.text)
            R.id.speaker_subj_pluperfect -> speak(tvSubjPluPerfect.text)
            R.id.speaker_subj_future_one -> speak(tvSubjFutureOne.text)
            R.id.speaker_subj_future_two -> speak(tvSubjFutureTwo.text)
        }
    }

    private fun speak(charSequence: CharSequence) {
        val text = charSequence.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}