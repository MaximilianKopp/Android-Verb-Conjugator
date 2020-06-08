package com.eloquence.verbconjugator

import android.os.Bundle
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.eloquence.verbconjugator.adapter.VerbPagerAdapter
import com.eloquence.verbconjugator.model.VerbViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_conjugation_tab.*
import kotlinx.android.synthetic.main.activity_main.*

class ConjugationTabActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conjugation_tab)

        val tabLayout = findViewById<TabLayout>(R.id.tablayout)
        viewPager = findViewById(R.id.view_pager)
        pagerAdapter = VerbPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            tabLayout.tabCount
        )
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = pagerAdapter

        tablayout.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                when (tab.position) {
                    0 -> pagerAdapter.notifyDataSetChanged()
                    1 -> pagerAdapter.notifyDataSetChanged()
                    2 -> pagerAdapter.notifyDataSetChanged()
                    3 -> pagerAdapter.notifyDataSetChanged()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }
}