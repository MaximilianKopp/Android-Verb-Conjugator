package com.eloquence.verbconjugator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eloquence.verbconjugator.data.PrepareData
import java.util.*

class Splashscreen : AppCompatActivity() {

    private val prepareData = PrepareData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_layout)

        prepareData.loadData(this, application)

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }, 3000)
    }
}