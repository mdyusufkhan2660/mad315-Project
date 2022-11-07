package com.example.spaceshooter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.group_3_project_mad315.R
import java.util.*
//sample line
class EnemySpaceship(context: Context) {

    var context: Context = context
    var enemySpaceship: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.spaceshooter_rocket2)
    var random: Random = Random()
    var ex = 200 + random!!.nextInt(400)
    var ey = 0
    var enemyVelocity = 10

    @JvmName("getEnemySpaceship1")
    fun getEnemySpaceship(): Bitmap {
        return enemySpaceship
    }

    fun getEnemySpaceshipWidth(): Int {
        return enemySpaceship!!.width
    }

    fun getEnemySpaceshipHeight(): Int {
        return enemySpaceship!!.height
    }


}