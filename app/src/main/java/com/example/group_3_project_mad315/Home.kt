package com.example.group_3_project_mad315

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn1 = findViewById<Button>(R.id.game_list)
        val btn2 = findViewById<Button>(R.id.random_game)

        btn1.setOnClickListener {
            val intent = Intent(this, Game_List::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val intent = Intent(this, Card_Game_Info::class.java)
            startActivity(intent)
        }

    }
}