package com.example.spaceshooter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.group_3_project_mad315.R

class Shot(context: Context, shx: Int, shy: Int) {
    var shot: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.spaceshooter_shot)
    var context: Context = context
    var shx = shx
    var shy = shy

    @JvmName("getShot1")
    fun getShot(): Bitmap {
        return shot
    }

    fun getShotWidth(): Int {
        return shot!!.width
    }

    fun getShotHeight(): Int {
        return shot!!.height
    }
}