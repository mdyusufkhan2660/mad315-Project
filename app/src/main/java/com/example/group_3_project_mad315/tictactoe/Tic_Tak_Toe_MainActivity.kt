package com.example.group_3_project_mad315.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.group_3_project_mad315.R

class Tic_Tak_Toe_MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.b1)
        button.setOnClickListener{
            intent = Intent(this,Tic_Tak_Toe_MainActivity::class.java)
            startActivity(intent)
        }
    }
}