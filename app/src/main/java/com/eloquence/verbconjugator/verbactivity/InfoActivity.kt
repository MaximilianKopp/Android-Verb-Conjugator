package com.eloquence.verbconjugator.verbactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.eloquence.verbconjugator.MainActivity
import com.eloquence.verbconjugator.R

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar.apply {
            this?.setHomeButtonEnabled(true)
            this?.setDisplayHomeAsUpEnabled(true)
        }

        toolbar.setNavigationOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        })
    }

}