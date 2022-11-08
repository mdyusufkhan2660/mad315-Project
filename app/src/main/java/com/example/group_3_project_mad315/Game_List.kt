package com.example.group_3_project_mad315

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.group_3_project_mad315.ballblock.Start
import com.example.group_3_project_mad315.tictactoe.TictacActivity
import com.example.hangmangame.Hangman_MainActivity
import com.example.mad315_spaceshooter.spaceshooter.SpaceShooter_MainActivity
import com.example.spaceshooter.StartUp

class Game_List : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)

        val game1 = findViewById<CardView>(R.id.game1)
        val game2 = findViewById<CardView>(R.id.game2)
        val game3 = findViewById<CardView>(R.id.game3)
        val game4 = findViewById<CardView>(R.id.game4)
        val gme1 = findViewById<TextView>(R.id.gme1)
        val gme2 = findViewById<TextView>(R.id.gme2)
        val gme3 = findViewById<TextView>(R.id.gme3)
        val gme4 = findViewById<TextView>(R.id.gme4)

        game1.setOnClickListener {
            startActivity(Intent(this, Start::class.java))

        }

        game2.setOnClickListener {
            startActivity(Intent(this, TictacActivity::class.java))

        }

        game3.setOnClickListener {
            startActivity(Intent(this, StartUp::class.java))

        }

        game4.setOnClickListener {
            startActivity(Intent(this, Hangman_MainActivity::class.java))

        }


        gme1.setOnClickListener {
            startActivity(Intent(this, Start::class.java))
        }
        gme2.setOnClickListener {
            startActivity(Intent(this, TictacActivity::class.java))

        }
        gme3.setOnClickListener {
            startActivity(Intent(this, StartUp::class.java))

        }
        gme4.setOnClickListener {
            startActivity(Intent(this, Hangman_MainActivity::class.java))

        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, Home::class.java))
    }
}