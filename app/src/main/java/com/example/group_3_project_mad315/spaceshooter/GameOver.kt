package com.example.spaceshooter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.group_3_project_mad315.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

class GameOver : AppCompatActivity()  {
    lateinit var sharedPreferences: SharedPreferences
    var editor: SharedPreferences.Editor? = null

    lateinit var tvPoints: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spaceshooter_gameover)
        val points = intent.extras!!.getInt("points")
        tvPoints = findViewById(R.id.tvPoints)
        tvPoints.setText("" + points)

        //Saving the username in sharedpreference
        sharedPreferences = getSharedPreferences("mad315", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor!!.putString("ss_score", tvPoints.text.toString())
        editor!!.apply()
        //Getting the username from shared preference
        Log.d("ss_score: ", sharedPreferences.getString("ss_score","0").toString())

        Firebase.firestore.collection("users").document(sharedPreferences.getString("name","0").toString()).get().addOnSuccessListener {
                result ->
            if(result.data!!.getValue("ss_score").toString().equals("")){
                savePoints(sharedPreferences.getString("name","test")!!, points)
                Log.d("Points: ", points.toString())
            }
            else if(Integer.parseInt(result.data!!.getValue("ss_score").toString()) < points){
                savePoints(sharedPreferences.getString("name","test")!!, points)
                Log.d("New High score Points: ", points.toString())
            }
        }
    }

    fun restart(view: View?) {
        val intent = Intent(this@GameOver, StartUp::class.java)
        startActivity(intent)
        finish()
    }

    fun exit(view: View?) {
        finish()
    }

    fun savePoints(id: String, points: Int) {
        Firebase.firestore.collection("users").document(id).update("ss_score", points.toString())
    }
}