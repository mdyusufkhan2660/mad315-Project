package com.example.hangmangame

sealed class GameState {
    class Running(val lettersUsed: String,
                  val underscoreWord: String,
                  val drawable: Int) : GameState()
    class Won(val wordToGuess: String) : GameState()
    class Lost(val wordToGuess: String) : GameState()
}