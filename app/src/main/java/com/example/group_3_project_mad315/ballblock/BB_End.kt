package com.example.group_3_project_mad315.ballblock

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.group_3_project_mad315.R

class End : AppCompatActivity() {


    lateinit var sharedPreferences: SharedPreferences
    var editor: SharedPreferences.Editor? = null

    var hs = 0
    // var s = 0
    var diff = 0

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bb_end)

        val iv_home = findViewById<ImageView>(R.id.iv_home)
        val btn_try = findViewById<TextView>(R.id.btn_try)
        val iv_retry = findViewById<ImageView>(R.id.iv_retry)
        val score = findViewById<TextView>(R.id.score)
        val highscore = findViewById<TextView>(R.id.highscore)
        // s = intent.extras!!.getInt("score")
        diff = intent.extras!!.getInt("difference")
        //score.setText(Integer.toString(s))
        score.setText(Integer.toString(diff))

        sharedPreferences = getSharedPreferences("ballredhs", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        hs = gethighscore(diff)
        highscore.setText(Integer.toString(hs))

        btn_try.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@End, Game::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        })

        iv_retry.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@End, Game::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        })

        iv_home.setOnClickListener {
            val i = Intent(this@End,Start::class.java)
            startActivity(i)
        }
    }

    private fun gethighscore(diff: Int): Int {
        var hs = sharedPreferences!!.getInt("hs", 0)
        if (hs < diff) {
            hs = diff
            editor!!.putInt("hs", diff)
            editor!!.apply()
        }
        return hs
    }
}
