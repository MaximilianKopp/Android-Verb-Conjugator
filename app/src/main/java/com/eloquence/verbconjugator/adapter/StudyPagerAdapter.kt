package com.eloquence.verbconjugator.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.eloquence.verbconjugator.studyFragment.*

class StudyPagerAdapter(fm: FragmentManager, behaviour: Int, private val numOfTabs: Int) :
    FragmentPagerAdapter(fm, behaviour) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return PresentTenseFragment()
            1 -> return PastTenseFragment()
            2 -> return PerfectTenseFragment()
            3 -> return PQPTenseFragment()
            4 -> return FutureOneTenseFragment()
            5 -> return FutureTwoTenseFragment()
        }
        return PresentTenseFragment()
    }

    override fun getCount(): Int {
        return numOfTabs
    }

    override fun getItemPosition(`object`: Any): Int {
        return if (`object` is Fragment) {
            PagerAdapter.POSITION_UNCHANGED
        } else {
            PagerAdapter.POSITION_NONE
        }
    }
}