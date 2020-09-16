package com.eloquence.verbconjugator.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.eloquence.verbconjugator.conjugatorFragment.ImperativeFragment
import com.eloquence.verbconjugator.conjugatorFragment.IndicativeFragment
import com.eloquence.verbconjugator.conjugatorFragment.InfPartFragment
import com.eloquence.verbconjugator.conjugatorFragment.SubjunctiveFragment

class VerbPagerAdapter(fm: FragmentManager, behaviour: Int, private val numOfTabs: Int) :
    FragmentPagerAdapter(fm, behaviour) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return IndicativeFragment()
            1 -> return SubjunctiveFragment()
            2 -> return ImperativeFragment()
            3 -> return InfPartFragment()
        }
        return IndicativeFragment()
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