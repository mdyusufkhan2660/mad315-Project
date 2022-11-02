package com.example.group_3_project_mad315

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Card_Game_Info : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_game_info)

        val btn = findViewById<Button>(R.id.play_card_game)
        btn.setOnClickListener {
            val intent = Intent(this, Card_Game::class.java)
            startActivity(intent)
        }

    }
}