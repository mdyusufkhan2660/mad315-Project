package com.example.spaceshooter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.group_3_project_mad315.R

class Explosion(context: Context, ox: Int, oy: Int) {
    var explosion = arrayOfNulls<Bitmap>(9)
    var explosionFrame = 0
    var eX = ox
    var eY = oy
    var context = context

    fun getExplosion(explosionFrame: Int): Bitmap? {
        explosion[0] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion0
        )
        explosion[1] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion1
        )
        explosion[2] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion2
        )
        explosion[3] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion3
        )
        explosion[4] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion4
        )
        explosion[5] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion5
        )
        explosion[6] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion6
        )
        explosion[7] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion7
        )
        explosion[8] = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.spaceshooter_explosion8
        )
        return explosion[explosionFrame]
    }
}