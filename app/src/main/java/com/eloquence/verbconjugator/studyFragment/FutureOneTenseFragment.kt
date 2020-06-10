package com.eloquence.verbconjugator.studyFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eloquence.verbconjugator.R

class FutureOneTenseFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = layoutInflater.inflate(R.layout.fragment_tense_futureone_tab, container, false)
        return view
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}