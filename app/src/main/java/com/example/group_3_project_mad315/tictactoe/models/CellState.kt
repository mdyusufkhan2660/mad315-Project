package com.example.group_3_project_mad315.tictactoe.models

import androidx.annotation.DrawableRes
import com.example.group_3_project_mad315.R

sealed class CellState(@DrawableRes val res: Int) {
    object Blank : CellState(R.drawable.ic_blank)
    object Star : CellState(R.drawable.ic_star)
    object Circle : CellState(R.drawable.ic_circle)
}
