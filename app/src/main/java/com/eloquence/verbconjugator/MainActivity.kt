package com.eloquence.verbconjugator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eloquence.verbconjugator.adapter.VerbAdapter
import com.eloquence.verbconjugator.model.Verb
import com.eloquence.verbconjugator.model.VerbViewModel
import com.eloquence.verbconjugator.studyactivity.StudyActivity
import com.eloquence.verbconjugator.verbactivity.ConjugationTabActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var verbAdapter: VerbAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var verbViewModel: VerbViewModel
    private lateinit var searchItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.main_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_main_view)
        navigationView.setNavigationItemSelectedListener(this)

        ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).apply { this.syncState() }

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            this.setOnNavigationItemSelectedListener(navListener)
        }

        verbAdapter = VerbAdapter(this).also {
            it.setOnItemClickListener(object : VerbAdapter.OnItemClickListener {
                override fun onItemClick(verb: Verb) {
                    val intent = Intent(this@MainActivity, ConjugationTabActivity::class.java)
                        .apply {
                            putExtra("verb", verb)
                        }
                    startActivity(intent)
                    searchItem.collapseActionView()
                }
            })
        }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerview).also {
            it.adapter = verbAdapter
            it.layoutManager = LinearLayoutManager(this)
            it.setHasFixedSize(true)
            it.itemAnimator = null
        }

        verbViewModel =
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(application)
                .create(VerbViewModel::class.java)

        verbViewModel.allVerbs.observe(this, Observer {
            verbAdapter.setVerbs(it)
        })
    }

    override fun onStart() {
        super.onStart()
        bottomNavigationView.selectedItemId = R.id.nav_home
        verbViewModel.allVerbs.observeForever {
            verbAdapter.setVerbs(it)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_weak -> verbViewModel.allWeakVerbs.observeForever {
                verbAdapter.setVerbs(it)
            }

            R.id.nav_strong -> verbViewModel.allStrongVerbs.observeForever {
                verbAdapter.setVerbs(it)
            }

            R.id.nav_reflexive -> verbViewModel.allReflexiveVerbs.observeForever {
                verbAdapter.setVerbs(it)
            }

            R.id.nav_separable -> verbViewModel.allSeparableVerbs.observeForever {
                verbAdapter.setVerbs(it)
            }

            R.id.nav_nonseparable -> verbViewModel.allNonSeparableVerbs.observeForever {
                verbAdapter.setVerbs(it)
            }

            R.id.nav_total -> verbViewModel.allVerbs.observeForever {
                verbAdapter.setVerbs(it)
            }
            R.id.nav_favourites -> verbViewModel.allFavourites.observeForever {
                verbAdapter.setVerbs(it)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        searchItem = menu!!.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                verbViewModel.getAllFilteredVerbs(newText).observeForever {
                    verbAdapter.setVerbs(it)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {

        when (it.itemId) {
            R.id.nav_home -> verbViewModel.allVerbs.observeForever { verb ->
                verbAdapter.setVerbs(verb)
            }

            R.id.nav_games -> null

            R.id.nav_study -> Intent(this, StudyActivity::class.java).apply {
                startActivity(this)
            }
        }
        return@OnNavigationItemSelectedListener true
    }
}
