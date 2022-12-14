package com.example.hangmangame

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.group_3_project_mad315.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Hangman_MainActivity : AppCompatActivity() {
    var score = 0
    lateinit var sharedPreferences: SharedPreferences
    var editor: SharedPreferences.Editor? = null


    private val gameManager = GameManager()

    private lateinit var wordTextView: TextView
    private lateinit var lettersUsedTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var gameLostTextView: TextView
    private lateinit var gameWonTextView: TextView
    private lateinit var newGameButton: ImageButton
    private lateinit var lettersLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hangman_activity_main)
        imageView = findViewById(R.id.imageView)
        wordTextView = findViewById(R.id.wordTextView)
//        lettersUsedTextView = findViewById(R.id.lettersUsedTextView)
        gameLostTextView = findViewById(R.id.gameLostTextView)
        gameWonTextView = findViewById(R.id.gameWonTextView)
        newGameButton = findViewById(R.id.newGameButton)
        lettersLayout = findViewById(R.id.lettersLayout)
        newGameButton.setOnClickListener {
            startNewGame()
        }
        val gameState = gameManager.startNewGame()
        updateUI(gameState)

        lettersLayout.children.forEach { letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    val gameState = gameManager.play((letterView).text[0])
                    updateUI(gameState)
                    letterView.visibility = View.GONE
                }
            }
        }
    }

    private fun updateUI(gameState: GameState) {
        when (gameState) {
            is GameState.Lost -> showGameLost(gameState.wordToGuess)
            is GameState.Running -> {
                wordTextView.text = gameState.underscoreWord
//                lettersUsedTextView.text ="Letters used: ${gameState.lettersUsed}"
                imageView.setImageDrawable(ContextCompat.getDrawable(this, gameState.drawable))
            }
            is GameState.Won -> showGameWon(gameState.wordToGuess)
        }
    }

    private fun showGameLost(wordToGuess: String){
        wordTextView.text = wordToGuess
        gameLostTextView.visibility = View.VISIBLE
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hangman))
        lettersLayout.visibility = View.GONE
    }


    private fun showGameWon(wordToGuess: String){
        wordTextView.text = wordToGuess
        gameWonTextView.visibility = View.VISIBLE
        lettersLayout.visibility = View.GONE
        score++
    }

    private fun startNewGame() {

        //Saving the username in sharedpreference
        sharedPreferences = getSharedPreferences("mad315", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor!!.putString("hm_score", score.toString())
        editor!!.apply()
        //Getting the username from shared preference
        Log.d("hm_score: ", sharedPreferences.getString("hm_score","0").toString())

        Firebase.firestore.collection("users").document(sharedPreferences.getString("name","0").toString()).get().addOnSuccessListener {
                result ->
            if(result.data!!.getValue("hm_score").toString().equals("")){
                savePoints(sharedPreferences.getString("name","test")!!, score)

            }
            else if(Integer.parseInt(result.data!!.getValue("hm_score").toString()) < score){
                savePoints(sharedPreferences.getString("name","test")!!, score)
            }
        }


        gameLostTextView.visibility = View.GONE
        gameWonTextView.visibility = View.GONE
        val gameState = gameManager.startNewGame()
        lettersLayout.visibility = View.VISIBLE
        lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }

    fun savePoints(id: String, points: Int) {
        Firebase.firestore.collection("users").document(id).update("hm_score", points.toString())
    }
}