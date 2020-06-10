package com.eloquence.verbconjugator.studyactivity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.eloquence.verbconjugator.R
import com.eloquence.verbconjugator.studyFragment.PresentTenseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class StudyActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigation: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.study_layout)
        navigation = findViewById(R.id.nav_study_view)
        navigation.setNavigationItemSelectedListener(this)

        ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).apply { this.syncState() }

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .apply {
                this.setOnNavigationItemSelectedListener(navListener)
            }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {


        return@OnNavigationItemSelectedListener true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_tenses -> Intent(this, TensesActivity::class.java).apply {
                startActivity(this)
                drawerLayout.closeDrawers()
            }

        }

        return false
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }
}
