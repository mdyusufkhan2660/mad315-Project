package com.example.group_3_project_mad315.tictactoe

import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.group_3_project_mad315.tictactoe.models.Board
import com.example.group_3_project_mad315.tictactoe.models.BoardState
import com.example.group_3_project_mad315.tictactoe.models.Cell
import com.example.group_3_project_mad315.tictactoe.models.CellState

class MainActivityViewModel : ViewModel() {



    val mainBoard = Board()
    val board = MutableLiveData(mainBoard)

    private fun updateBoard() {
        board.value = mainBoard
    }

    fun boardClicked(cell: Cell) {
        if (mainBoard.setCell(cell, CellState.Star)) {
            updateBoard()
            if (mainBoard.boardState == BoardState.INCOMPLETE) {
                aiTurn()
            }
        }
    }

    private fun aiTurn() {


        val circleWinningCell = mainBoard.findNextWinningMove(CellState.Circle)
        val startWinningCell = mainBoard.findNextWinningMove(CellState.Star)
        val max = 5
        val min = 1
        val ran = Math.floor(Math.random() * max- min).toInt()


        when {



            // If the AI can win, place a circle in that spot
            circleWinningCell != null -> mainBoard.setCell(circleWinningCell, CellState.Circle)
            // If the AI is about to lose, place a circle in a blocking spot
            startWinningCell != null -> mainBoard.setCell(startWinningCell, CellState.Circle)

            // Prioritize the middle
            mainBoard.setCell(Cell.CENTER_CENTER, CellState.Circle) -> Unit

            // Otherwise place a circle in a random spot

            else -> do {
                val nextCell = Cell.values().random()
                val placeSuccess = mainBoard.setCell(nextCell, CellState.Circle)
            } while (!placeSuccess)
        }


        updateBoard()

    }


    fun resetBoard() {
        mainBoard.clearBoard()
        updateBoard()
    }
}