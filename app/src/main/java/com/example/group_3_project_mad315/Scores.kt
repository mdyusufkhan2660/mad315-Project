package com.example.group_3_project_mad315

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Scores : AppCompatActivity() {
    var bb_max = 0
    var ttt_max = 0
    var ss_max = 0
    var hm_max = 0

    lateinit var db_sharedPref: SharedPreferences
    var db_editor: SharedPreferences.Editor? = null

    lateinit var userscore_spaceShooter: TextView
    lateinit var userscore_ballblock: TextView
    lateinit var userscore_tictactoe: TextView
    lateinit var userscore_hangman: TextView

    lateinit var highscore_spaceShooter: TextView
    lateinit var highscore_ballblock: TextView
    lateinit var highscore_tictactoe: TextView
    lateinit var highscore_hangman: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        userscore_ballblock = findViewById(R.id.bbuserscore)
        userscore_tictactoe = findViewById(R.id.tttuserscore)
        userscore_spaceShooter = findViewById(R.id.ssuserscore)
        userscore_hangman = findViewById(R.id.hmuserscore)

        highscore_ballblock = findViewById(R.id.bboverallscore)
        highscore_tictactoe = findViewById(R.id.tttoverallscore)
        highscore_spaceShooter = findViewById(R.id.ssoverallscore)
        highscore_hangman = findViewById(R.id.hmoverallscore)

        db_sharedPref = getSharedPreferences("mad315", MODE_PRIVATE)
        var user = db_sharedPref.getString("name","0").toString()

        findUserScore(user)
        findMaxScore()

    }
    fun findUserScore(user: String){
        Firebase.firestore.collection("users").document(user).get().addOnSuccessListener{
            result->
            userscore_ballblock.setText(result.data!!.get("bb_score").toString())
            userscore_tictactoe.setText(result.data!!.get("ttt_score").toString())
            userscore_spaceShooter.setText(result.data!!.get("ss_score").toString())
            userscore_hangman.setText(result.data!!.get("hm_score").toString())
        }

    }
    fun findMaxScore(){
        Firebase.firestore.collection("users").get().addOnSuccessListener{
                result->
            for (document in result){
                if(document.data.get("bb_score").toString().length >= 1){
                    if(bb_max < Integer.parseInt(document.data.get("bb_score").toString())){
                        bb_max = Integer.parseInt(document.data.get("bb_score").toString())
                    }
                }
                if(document.data.get("ss_score").toString().length >= 1){
                    if(ss_max < Integer.parseInt(document.data.get("ss_score").toString())){
                        ss_max = Integer.parseInt(document.data.get("ss_score").toString())
                    }
                }
                if(document.data.get("ttt_score").toString().length >= 1){
                    if(ttt_max < Integer.parseInt(document.data.get("ttt_score").toString())){
                        ttt_max = Integer.parseInt(document.data.get("ttt_score").toString())
                    }
                }
                if(document.data.get("hm_score").toString().length >= 1){
                    if(hm_max < Integer.parseInt(document.data.get("hm_score").toString())){
                        hm_max = Integer.parseInt(document.data.get("hm_score").toString())
                    }
                }
            }
            highscore_ballblock.setText(bb_max.toString())
            highscore_spaceShooter.setText(ss_max.toString())
            highscore_tictactoe.setText(ttt_max.toString())
            highscore_hangman.setText(hm_max.toString())

        }
    }
}