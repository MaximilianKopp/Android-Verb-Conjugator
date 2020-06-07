package com.eloquence.verbconjugator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eloquence.verbconjugator.adapter.VerbAdapter
import com.eloquence.verbconjugator.model.VerbViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val verbAdapter = VerbAdapter(this)
        recyclerView.adapter = verbAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val verbViewModel =
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(application)
                .create(VerbViewModel::class.java)

        verbViewModel.allVerbs.observe(this, Observer {
            verbAdapter.setVerbs(it)
        })
    }
}
