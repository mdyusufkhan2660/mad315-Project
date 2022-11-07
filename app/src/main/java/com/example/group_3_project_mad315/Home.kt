package com.example.group_3_project_mad315

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {

    val db = Firebase.firestore
    var userNames: ArrayList<String> = ArrayList()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn1 = findViewById<Button>(R.id.game_list)
        val btn2 = findViewById<Button>(R.id.random_game)
        val user_name = findViewById<EditText>(R.id.user_name)

        btn1.setOnClickListener {
            if(userName_nullcheck(user_name)){
                getUserNames()
                if (userNames.contains(user_name.text.toString())){
                    runGamelist()
                    Log.d("TAG", "name found")
                }
                else{
                    addName(user_name)
                    Log.d("TAG", "name not found")
                }
            }
        }

        btn2.setOnClickListener {
            if (userName_nullcheck(user_name)){
                val intent = Intent(this, Card_Game_Info::class.java)
                startActivity(intent)
            }
        }

    }

    fun userName_nullcheck(user_name: EditText):Boolean{
        if(user_name.text.length < 1){
            Toast.makeText(this,"No username given!",Toast.LENGTH_SHORT)
            return false
        }
        else{
            Toast.makeText(this,user_name.text,Toast.LENGTH_SHORT)
            return true
        }
    }

    fun getUserNames(){
        db.collection("users").get().addOnSuccessListener {
                result ->
                for (document in result) {
                        userNames.add(document.data.getValue("name").toString())
                    }
                }
            .addOnFailureListener {
            }
    }

    fun addName(user_name: EditText){
        val user = hashMapOf(
            "name" to user_name.text.toString(),
            "bb_score" to "",
            "hm_score" to "",
            "ss_score" to "",
            "ttt_score" to ""
        )
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->

                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                runGamelist()
            }
            .addOnFailureListener { e ->
                Log.w("Tag", "Error adding document", e)

            }
    }

    fun runGamelist(){
        val intent = Intent(this, Game_List::class.java)
        startActivity(intent)
    }
}