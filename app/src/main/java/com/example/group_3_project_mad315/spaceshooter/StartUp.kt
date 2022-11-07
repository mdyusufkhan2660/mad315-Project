package com.example.spaceshooter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.group_3_project_mad315.R
import com.example.mad315_spaceshooter.spaceshooter.SpaceShooter_MainActivity


class StartUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spaceshooter_startup)
    }

    fun startGame(view: View?) {
        startActivity(Intent(this@StartUp, SpaceShooter_MainActivity::class.java))
        finish()
    }
}