package com.eloquence.verbconjugator.conjugatorFragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eloquence.verbconjugator.R
import com.eloquence.verbconjugator.model.Verb
import com.eloquence.verbconjugator.model.VerbViewModel
import kotlinx.android.synthetic.main.fragment_ind_tab.view.*
import java.util.*

class IndicativeFragment : Fragment(), View.OnClickListener {

    private lateinit var tvParticipleParts: TextView
    private lateinit var tvIndPresent: TextView
    private lateinit var tvIndPast: TextView
    private lateinit var tvIndPerfect: TextView
    private lateinit var tvIndPluPerfect: TextView
    private lateinit var tvIndFutureOne: TextView
    private lateinit var tvIndFutureTwo: TextView
    private lateinit var tts: TextToSpeech

    private lateinit var cbFavourites: CheckBox
    private lateinit var verbViewModel: VerbViewModel
    private lateinit var verb: Verb


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = layoutInflater.inflate(R.layout.fragment_ind_tab, container, false)
        val bundle = activity?.intent?.extras
        verb = bundle?.getParcelable("verb")!!

        val tvParticiplePartsSpeaker = view.findViewById<TextView>(R.id.speaker_participle_parts)
        tvParticiplePartsSpeaker.text = verb.infinitivePresent
        tvParticiplePartsSpeaker.setOnClickListener(this)
        tvParticipleParts = view.findViewById(R.id.participle_parts)
        tvParticipleParts.text = String.format(
            "%s %s %s", verb.infinitivePresent, verb.pastform, verb.pastparticiple
        )

        val tvIndPresentSpeaker = view.findViewById<TextView>(R.id.speaker_ind_present)
        tvIndPresentSpeaker.setOnClickListener(this)
        tvIndPresent = view.findViewById(R.id.ind_present)
        tvIndPresent.text = verb.indicativePresent

        val tvIndPastSpeaker = view.findViewById<TextView>(R.id.speaker_ind_past)
        tvIndPastSpeaker.setOnClickListener(this)
        tvIndPast = view.findViewById(R.id.ind_past)
        tvIndPast.text = verb.indicativePast

        val tvIndPerfectSpeaker = view.findViewById<TextView>(R.id.speaker_ind_perfect)
        tvIndPerfectSpeaker.setOnClickListener(this)
        tvIndPerfect = view.findViewById(R.id.ind_perfect)
        tvIndPerfect.text = verb.indicativePerfect

        val tvIndPluPerfectSpeaker =
            view.findViewById<TextView>(R.id.speaker_ind_pluperfect)
        tvIndPluPerfectSpeaker.setOnClickListener(this)
        tvIndPluPerfect = view.findViewById(R.id.ind_pluperfect)
        tvIndPluPerfect.text = verb.indicativePluPerfect

        val tvIndFutureOneSpeaker =
            view.findViewById<TextView>(R.id.speaker_ind_future_one)
        tvIndFutureOneSpeaker.setOnClickListener(this)
        tvIndFutureOne = view.findViewById(R.id.ind_future_one)
        tvIndFutureOne.text = verb.indicativeFutureOne

        val tvIndFutureTwoSpeaker =
            view.findViewById<TextView>(R.id.speaker_ind_future_two)
        tvIndFutureTwoSpeaker.setOnClickListener(this)
        tvIndFutureTwo = view.findViewById(R.id.ind_future_two)
        tvIndFutureTwo.text = verb.indicativeFutureTwo

        cbFavourites = view.findViewById(R.id.cb_favourites)
        cbFavourites.setOnClickListener(this)

        verbViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
                .create(
                    VerbViewModel::class.java
                )

        cbFavourites.isChecked = verb.isFavourite

        tts = TextToSpeech(context, OnInitListener { status ->
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
            R.id.speaker_participle_parts -> speak(tvParticipleParts.text)
            R.id.speaker_ind_present -> speak(tvIndPresent.text)
            R.id.speaker_ind_past -> speak(tvIndPast.text)
            R.id.speaker_ind_perfect -> speak(tvIndPerfect.text)
            R.id.speaker_ind_pluperfect -> speak(tvIndPluPerfect.text)
            R.id.speaker_ind_future_one -> speak(tvIndFutureOne.text)
            R.id.speaker_ind_future_two -> speak(tvIndFutureTwo.text)
            R.id.cb_favourites ->
                if (cbFavourites.isChecked) {
                    verb.isFavourite = true
                    verbViewModel.update(verb)
                } else {
                    verb.isFavourite = false
                    verbViewModel.update(verb)
                }
        }
    }

    private fun speak(charSequence: CharSequence) {
        val text = charSequence.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}