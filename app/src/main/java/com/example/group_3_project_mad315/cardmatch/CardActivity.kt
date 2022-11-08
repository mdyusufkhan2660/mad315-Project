package com.example.group_3_project_mad315

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.group_3_project_mad315.ballblock.Start
import com.example.group_3_project_mad315.tictactoe.TictacActivity
import com.example.hangmangame.Hangman_MainActivity
import com.example.spaceshooter.StartUp
import kotlinx.android.synthetic.main.activity_card.*

private const val TAG = "CardActivity"
class CardActivity : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val images = mutableListOf(
            R.drawable.tictactoe,
            R.drawable.ballandblock,
            R.drawable.hangman,
            R.drawable.spaceshooter
        )
        // Add each image twice so we can create pairs
        images.addAll(images)
        // Randomize the order of images
        images.shuffle()

        buttons = listOf(imageButton1, imageButton2, imageButton3, imageButton4, imageButton5,
                imageButton6, imageButton7, imageButton8)

        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "button clicked!!")
                // Update models
                updateModels(index)
                // Update the UI for the game
                updateViews()
            }
        }
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else R.drawable.question)
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        // Error checking:
        if (card.isFaceUp) {
            Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show()
            return
        }
        // Three cases
        // 0 cards previously flipped over => restore cards + flip over the selected card
        // 1 card previously flipped over => flip over the selected card + check if the images match
        // 2 cards previously flipped over => restore cards + flip over the selected card
        if (indexOfSingleSelectedCard == null) {
            // 0 or 2 selected cards previously
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            // exactly 1 card was selected previously
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier) {

         //   Toast.makeText(this, "Match found!!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true

            Log.i("tag",cards[position1].identifier.toString())

            if(cards[position1].identifier==2131231010){
//                Toast.makeText(this, "heart launched", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TictacActivity::class.java)
                startActivity(intent)

            }
            else if (cards[position1].identifier==2131230990)
            {
                // Toast.makeText(this, "lightening launched", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, StartUp::class.java)
                startActivity(intent)


            }
            else if (cards[position1].identifier==2131230836)
            {
                Toast.makeText(this, "Ball and Block ", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@CardActivity, Start::class.java))


            }
            else if (cards[position1].identifier==2131230889)
            {

                val intent = Intent(this@CardActivity,  Hangman_MainActivity::class.java)
                startActivity(intent)



            }


        }

    }

}
