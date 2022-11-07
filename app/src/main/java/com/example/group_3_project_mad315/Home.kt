package com.example.group_3_project_mad315

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Home : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn1 = findViewById<Button>(R.id.game_list)
        val btn2 = findViewById<Button>(R.id.random_game)

        btn1.setOnClickListener {
            if(userName_check()){
                val intent = Intent(this, Game_List::class.java)
                startActivity(intent)
            }
        }

        btn2.setOnClickListener {
            if (userName_check()){
                val intent = Intent(this, Card_Game_Info::class.java)
                startActivity(intent)
            }
        }

    }

    fun userName_check():Boolean{
        val user_name = findViewById<EditText>(R.id.user_name)
        if(user_name.text.equals("")){
            Toast.makeText(this,"No username given!",Toast.LENGTH_SHORT)
            return false
        }
        else{
            return true
        }
    }
}