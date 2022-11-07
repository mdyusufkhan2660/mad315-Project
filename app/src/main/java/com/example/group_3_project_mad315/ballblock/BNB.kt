package com.example.group_3_project_mad315.ballblock

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.group_3_project_mad315.R
import java.security.AccessController
import kotlin.math.floor

class BNB(context: Context?) : View(context) {

    private var ball : Bitmap
    var background: Bitmap
    private var redblock: Bitmap
    private var blueblock: Bitmap
    private var yellowblock: Bitmap
    private var greenblock: Bitmap

    private var width: Int? = 0
    private var height: Int? = 0

    private  var ballx:Int = 200
    var bally:Int = 0
    private var ballw: Int = 0
    private var ballh: Int = 0
    private  var ballspeed:Int = 0

    private  var minbally:Int = 0
    private  var maxbally:Int = 0

    private var touchscreen = false

    private var yellowx = 0
    private var yellowy = 0
    private var yellowspeed = 0

    private var greenx = 0
    private var greeny = 0
    private var greenspeed = 0

    private var bluex = 0
    private var bluey = 0
    private var bluespeed = 0

    private var redx = 0
    private var redy = 0
    private var redspeed = 0

    var ys = 0
    var gs = 0
    var bs = 0
    var rs = 0

    private var startTime = 0

    private val minballx = 0
    private val maxballx = 0

    var timerStop = false


    init {
        startTime = System.currentTimeMillis().toInt()

        ball = BitmapFactory.decodeResource(resources, R.drawable.bb_ball)
        ball = Bitmap.createScaledBitmap(ball, 30, 30, false)

        val metrics : DisplayMetrics = getContext().resources.displayMetrics
        width = metrics.widthPixels
        height = metrics.heightPixels

        background = BitmapFactory.decodeResource(resources, R.drawable.bb_background)
        background = Bitmap.createScaledBitmap(background, width!!, height!!, false)

        ballw = ball.width
        ballh = ball.height

        val blockwidth = floor(Math.random() + 90).toInt()

        yellowblock = BitmapFactory.decodeResource(resources, R.drawable.bb_yellowb)
        yellowblock = Bitmap.createScaledBitmap(yellowblock, blockwidth, 30, false)

        greenblock = BitmapFactory.decodeResource(resources, R.drawable.bb_greenb)
        greenblock = Bitmap.createScaledBitmap(greenblock, blockwidth, 30, false)

        blueblock = BitmapFactory.decodeResource(resources, R.drawable.bb_blueb)
        blueblock = Bitmap.createScaledBitmap(blueblock, blockwidth, 30, false)

        redblock = BitmapFactory.decodeResource(resources, R.drawable.bb_redb)
        redblock = Bitmap.createScaledBitmap(redblock, blockwidth, 30, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(yellowspeed == 0 || greenspeed == 0 || bluespeed == 0 || redspeed == 0 ){
            ys = 4
            gs = 7
            bs = 5
            rs = 6
        }
        yellowspeed = ys
        greenspeed = gs
        bluespeed = bs
        redspeed = rs

        canvas.drawBitmap(background, 0f, 0f, null)
        minbally = ball.height
        maxbally = height!! - ball.height * 2
        bally += ballspeed

        if (bally < minbally) bally = minbally
        if (bally > maxbally) {
            bally = maxbally
        }
        ballspeed += 2

        yellowx -= yellowspeed

        if (collision(yellowx, yellowy, yellowblock.width,  yellowblock.height)) {

            yellowx = -100
            val difference: Int = (System.currentTimeMillis().toInt() - startTime) / 1000
            val intent = Intent(context, End::class.java)
            intent.putExtra("difference", difference)

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }

        if (yellowx < -400) {
            yellowx = width!! + 21
            yellowy = floor(Math.random() * (maxbally - minbally)).toInt() + minbally

            val blockwidth = floor((Math.random() * 500) + 70).toInt()
            yellowblock = Bitmap.createScaledBitmap(yellowblock, blockwidth, 30, false)

        }
        canvas.drawBitmap(yellowblock, yellowx.toFloat(), yellowy.toFloat(), null)

        greenx -= greenspeed

        if (collision(greenx, greeny, greenblock.width, greenblock.height)) {
            greenx = -100
            val difference: Int = (System.currentTimeMillis().toInt() - startTime) / 1000
            val intent = Intent(context, End::class.java)
            intent.putExtra("difference", difference)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)

        }
        if (greenx < -400) {
            greenx = width!! + 100
            greeny = floor(Math.random() * (maxbally - minbally)).toInt() + minbally

            val blockwidth = floor((Math.random() * 500) + 70).toInt()
            greenblock = Bitmap.createScaledBitmap(greenblock, blockwidth, 30, false)
        }
        canvas.drawBitmap(greenblock, greenx.toFloat(), greeny.toFloat(), null)

        bluex -= bluespeed
        if (collision(bluex, bluey, blueblock.width,  blueblock.height)) {

            bluex = -100
            val difference: Int = (System.currentTimeMillis().toInt() - startTime) / 1000

            val intent = Intent(context, End::class.java)
            intent.putExtra("difference", difference)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
        if(bluex < -400)
        {
            bluex = width!! + 70
            bluey = floor(Math.random() * (maxbally - minbally)).toInt() + minbally

            val blockwidth = floor((Math.random() * 500) + 70).toInt()
            blueblock = Bitmap.createScaledBitmap(blueblock, blockwidth, 30, false)

        }
        canvas.drawBitmap(blueblock, bluex.toFloat(), bluey.toFloat(), null)

        redx -= redspeed
        if (collision(redx, redy, redblock.width, redblock.height)) {
            redx = -100
            val difference: Int = (System.currentTimeMillis().toInt() - startTime) / 1000

            val intent = Intent(context, End::class.java)
            intent.putExtra("difference", difference)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)

        }
        if (redx < -400) {
            redx = width!! + 50
            redy = floor(Math.random() * (maxbally - minbally)).toInt() + minbally

            val blockwidth = floor((Math.random() * 500) + 70).toInt()
            redblock = Bitmap.createScaledBitmap(redblock, blockwidth, 30, false)
        }
        canvas.drawBitmap(redblock, redx.toFloat(),redy.toFloat(), null)

        if (touchscreen) {
            canvas.drawBitmap(ball, ballx.toFloat(), bally.toFloat(), null)
            touchscreen = false
        } else {
            canvas.drawBitmap(ball, ballx.toFloat(), bally.toFloat(), null)
        }

    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        val metrics: DisplayMetrics = context.resources.displayMetrics
        val i = metrics.heightPixels - 70
        if (bally >= i) {
            timerStop = true
            val difference: Int = (System.currentTimeMillis().toInt() - startTime) / 1000
            val intent = Intent(context, End::class.java)
            intent.putExtra("difference", difference)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            touchscreen = true
            ballspeed = -20
        }
        return true
    }

    private var ret : Boolean = false
    private fun collision(x: Int, y: Int, w: Int, h: Int): Boolean {

        val ball: Rect = Rect(ballx, bally, ballx + ballw, bally + ballh)
        val bblock: Rect = Rect(x, y, x + w, y + h)

        if (ball.intersect(bblock)) {
            ret =return true
        }
        return ret
    }

}