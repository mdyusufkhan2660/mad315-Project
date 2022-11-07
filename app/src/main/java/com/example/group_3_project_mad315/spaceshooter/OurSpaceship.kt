package com.example.spaceshooter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.group_3_project_mad315.R
import java.util.*

class OurSpaceship(context: Context) {

    var context: Context = context
    var ourSpaceship: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.spaceshooter_rocket1)
    var random: Random = Random()
    var ox = random!!.nextInt(SpaceShooter.screenWidth)
    var oy = SpaceShooter.screenHeight - ourSpaceship.getHeight()

    @JvmName("getOurSpaceship1")
    fun getOurSpaceship(): Bitmap {
        return ourSpaceship
    }

    fun getOurSpaceshipWidth(): Int {
        return ourSpaceship!!.width
    }
}