package com.example.spaceshooter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.group_3_project_mad315.R

class GameOver : AppCompatActivity()  {
    lateinit var tvPoints: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spaceshooter_gameover)
        val points = intent.extras!!.getInt("points")
        tvPoints = findViewById(R.id.tvPoints)
        tvPoints.setText("" + points)
    }

    fun restart(view: View?) {
        val intent = Intent(this@GameOver, StartUp::class.java)
        startActivity(intent)
        finish()
    }

    fun exit(view: View?) {
        finish()
    }
}