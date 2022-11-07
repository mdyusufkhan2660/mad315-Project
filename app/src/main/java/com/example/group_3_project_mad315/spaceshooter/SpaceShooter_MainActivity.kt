package com.example.mad315_spaceshooter.spaceshooter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spaceshooter.SpaceShooter

class SpaceShooter_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SpaceShooter(this))
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
    }
}