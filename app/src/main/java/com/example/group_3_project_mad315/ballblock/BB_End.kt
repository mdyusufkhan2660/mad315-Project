package com.example.group_3_project_mad315.ballblock

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.group_3_project_mad315.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class End : AppCompatActivity() {

    lateinit var db_sharedPref: SharedPreferences
    var db_editor: SharedPreferences.Editor? = null


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

            //Saving the username in sharedpreference
            db_sharedPref = getSharedPreferences("mad315", MODE_PRIVATE)
            db_editor = sharedPreferences.edit()
            db_editor!!.putString("bb_score", diff.toString())
            db_editor!!.apply()
            //Getting the username from shared preference
            Log.d("bb_score: ", sharedPreferences.getString("bb_score","0").toString())

            Firebase.firestore.collection("users").get().addOnSuccessListener {
                    result ->
                for (document in result) {
                    if (document.data.getValue("name").toString().equals(db_sharedPref.getString("name","0").toString())){
                        if(document.data.getValue("bb_score").toString().equals("")){
                            savePoints(document.id, diff)
                            Log.d("Points: ", diff.toString())
                        }
                        else if(Integer.parseInt(document.data.getValue("bb_score").toString()) < diff){
                            savePoints(document.id, diff)
                            Log.d("New High score Points: ", diff.toString())
                        }
                    }
                }
            }
        }
        return hs
    }

    fun savePoints(id: String, points: Int) {
        Firebase.firestore.collection("users").document(id).update("bb_score", points.toString())
    }
}
