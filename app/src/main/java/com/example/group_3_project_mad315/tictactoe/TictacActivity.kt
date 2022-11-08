package com.example.group_3_project_mad315.tictactoe

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.group_3_project_mad315.Game_List

import com.example.group_3_project_mad315.R

import com.example.group_3_project_mad315.databinding.ActivityTictacBinding
import com.example.group_3_project_mad315.databinding.ActivityMainBinding
import com.example.group_3_project_mad315.tictactoe.models.Board
import com.example.group_3_project_mad315.tictactoe.models.BoardState
import com.example.group_3_project_mad315.tictactoe.models.Cell
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TictacActivity : AppCompatActivity() {
    lateinit var db_sharedPref: SharedPreferences
    var db_editor: SharedPreferences.Editor? = null

    var totalScore = 0
    private var starsScore = 0
    private var crossScore = 0
    private var game_over:Boolean=false
    private var count = 0

    lateinit var binding: ActivityTictacBinding
    val vm: MainActivityViewModel by viewModels()
    private lateinit var sharedPref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTictacBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        vm.board.observe(this, updateBoard)
        bindClickEvents()
    }

    val updateBoard = Observer<Board> { board ->

        binding.square0.setImageResource(board.topLeft.res)
        binding.square1.setImageResource(board.topCenter.res)
        binding.square2.setImageResource(board.topRight.res)
        binding.square3.setImageResource(board.centerLeft.res)
        binding.square4.setImageResource(board.centerCenter.res)
        binding.square5.setImageResource(board.centerRight.res)
        binding.square6.setImageResource(board.bottomLeft.res)
        binding.square7.setImageResource(board.bottomCenter.res)
        binding.square8.setImageResource(board.bottomRight.res)

        when (board.boardState) {

           BoardState.STAR_WON -> {
                totalScore++
                starsScore ++
                addScore()
                setupBoard("Stars WON")
                showWinningMessage("Stars Won!")
            }
            BoardState.CIRCLE_WON -> {
                crossScore ++
                setupBoard("Computer Won")
                showWinningMessage("Computer Won!")
            }
            BoardState.DRAW -> {
                setupBoard("Draw")
                showWinningMessage("It's a Draw!")
            }
            BoardState.INCOMPLETE -> {
                setupBoard()
                hideWinningMessage()
            }

        }



    }


    private fun setupBoard(disable: Boolean = false) {
        binding.square0.isEnabled = !disable
        binding.square1.isEnabled = !disable
        binding.square2.isEnabled = !disable
        binding.square3.isEnabled = !disable
        binding.square4.isEnabled = !disable
        binding.square5.isEnabled = !disable
        binding.square6.isEnabled = !disable
        binding.square7.isEnabled = !disable
        binding.square8.isEnabled = !disable

        binding.square0.alpha = if (disable) 0.5f else 1f
        binding.square1.alpha = if (disable) 0.5f else 1f
        binding.square2.alpha = if (disable) 0.5f else 1f
        binding.square3.alpha = if (disable) 0.5f else 1f
        binding.square4.alpha = if (disable) 0.5f else 1f
        binding.square5.alpha = if (disable) 0.5f else 1f
        binding.square6.alpha = if (disable) 0.5f else 1f
        binding.square7.alpha = if (disable) 0.5f else 1f
        binding.square8.alpha = if (disable) 0.5f else 1f
    }

    private fun bindClickEvents() {
        binding.square0.setOnClickListener { vm.boardClicked(Cell.TOP_LEFT) }
        binding.square1.setOnClickListener { vm.boardClicked(Cell.TOP_CENTER) }
        binding.square2.setOnClickListener { vm.boardClicked(Cell.TOP_RIGHT) }
        binding.square3.setOnClickListener { vm.boardClicked(Cell.CENTER_LEFT) }
        binding.square4.setOnClickListener { vm.boardClicked(Cell.CENTER_CENTER) }
        binding.square5.setOnClickListener { vm.boardClicked(Cell.CENTER_RIGHT) }
        binding.square6.setOnClickListener { vm.boardClicked(Cell.BOTTOM_LEFT) }
        binding.square7.setOnClickListener { vm.boardClicked(Cell.BOTTOM_CENTER) }
        binding.square8.setOnClickListener { vm.boardClicked(Cell.BOTTOM_RIGHT) }
        binding.buttonReset.setOnClickListener { vm.resetBoard() }
    }

    private fun showWinningMessage(message: String) {
        binding.textWinningMessage.visibility = View.VISIBLE
        binding.textWinningMessage.text = message
    }


    private fun hideWinningMessage() {
        binding.textWinningMessage.visibility = View.GONE
    }
    private fun setupBoard(title: String)
    {
        count++
        Log.i("tag",count.toString())
        if( game_over!=true){
            if (count >= 5 && count<6) {
                setupBoard("Game Over")

                starsScore = 0
                crossScore = 0
                game_over = true
            }
        }
        if (game_over!=true) {
            val message = "\nUSER $starsScore\n\nComputer $crossScore"


            AlertDialog.Builder(this, )
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Reset")


                { _, _ ->

                    vm.resetBoard()


                }


                .setCancelable(false)
                .show()
         .window?.setGravity(Gravity.TOP)


        }
        if(game_over==true){
            game_over=false
            val editor =sharedPref.edit()
            editor.putInt("startsscore",starsScore)
            editor.putInt("crossscore",crossScore)
            editor.apply()
            count=0
        }
    }

    override fun onBackPressed(){
        val i = Intent(this@TictacActivity, Game_List::class.java)
        startActivity(i)
    }
    fun addScore() {
        //Saving the username in sharedpreference
        db_sharedPref = getSharedPreferences("mad315", MODE_PRIVATE)
        db_editor = db_sharedPref.edit()
        db_editor!!.putString("ttt_score", totalScore.toString())
        db_editor!!.apply()
        //Getting the username from shared preference
        Log.d("ttt_score: ", db_sharedPref.getString("ttt_score","0").toString())

        Firebase.firestore.collection("users").get().addOnSuccessListener {
                result ->
            for (document in result) {
                if (document.data.getValue("name").toString().equals(db_sharedPref.getString("name","0").toString())){
                    if(document.data.getValue("ttt_score").toString().equals("")){
                        savePoints(document.id, totalScore)
                        Log.d("Points: ", totalScore.toString())
                    }
                    else if(Integer.parseInt(document.data.getValue("ttt_score").toString()) < totalScore){
                        savePoints(document.id, totalScore)
                        Log.d("New High score Points: ", totalScore.toString())
                    }
                }
            }
        }
    }

    fun savePoints(id: String, points: Int) {
        Firebase.firestore.collection("users").document(id).update("ttt_score", points.toString())
    }
}