package com.eloquence.verbconjugator.studyactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.eloquence.verbconjugator.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tenses.*

class TensesActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenses)

        val tabLayout = findViewById<TabLayout>(R.id.study_tablayout)
        viewPager = findViewById(R.id.study_view_pager)

        viewPager.offscreenPageLimit = 3
        viewPager.adapter = pagerAdapter

        study_tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                when (tab.position) {
                    0 -> pagerAdapter.notifyDataSetChanged()
                    1 -> pagerAdapter.notifyDataSetChanged()
                    2 -> pagerAdapter.notifyDataSetChanged()
                    3 -> pagerAdapter.notifyDataSetChanged()
                    4 -> pagerAdapter.notifyDataSetChanged()
                    5 -> pagerAdapter.notifyDataSetChanged()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })


    }
}