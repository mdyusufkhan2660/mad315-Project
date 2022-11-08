package com.example.group_3_project_mad315.ballblock

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.group_3_project_mad315.Home
import com.example.group_3_project_mad315.R
import java.io.InputStream
import kotlin.system.exitProcess

class Start : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bb_start)
        val btn_start = findViewById<TextView>(R.id.btn_start)
        btn_start.setOnClickListener {
            val i = Intent(this@Start, Game::class.java)
            startActivity(i)
        }

        val btn_y = findViewById<TextView>(R.id.btn_y)
        btn_y.setOnClickListener {
            val i = Intent(this@Start, Game::class.java)
            startActivity(i)
        }

        val iv_play = findViewById<ImageView>(R.id.iv_play)
        iv_play.setOnClickListener {
            val i = Intent(this@Start, Game::class.java)
            startActivity(i)
        }

        val iv_exit = findViewById<ImageView>(R.id.iv_exit)
        iv_exit.setOnClickListener {
            val i = Intent(this,Home::class.java)
            startActivity(i)
        }
    }
}