package com.example.group_3_project_mad315.ballblock


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import com.example.group_3_project_mad315.R
import java.security.AccessController.getContext
import java.util.*

class Game : AppCompatActivity() {

    private lateinit var bnbview : BNB
    private val handler = Handler()
    private val interval = 25
    val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bb_game)


        bnbview = BNB(this)
        setContentView(bnbview)


        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (bnbview.timerStop) {
                        timer.cancel()
                    } else {
                        bnbview.invalidate()
                    }
                }
            }
        }, 0, interval.toLong())

        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                bnbview.ys = 8
                bnbview.gs = 10
                bnbview.bs = 7
                bnbview.rs = 9
                cancel()
            }
        }.start()

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                bnbview.ys = 14
                bnbview.gs = 16
                bnbview.bs = 18
                bnbview.rs = 20
                cancel()
            }
        }.start()

        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                bnbview.ys = 20
                bnbview.gs = 24
                bnbview.bs = 23
                bnbview.rs = 27
                cancel()
            }
        }.start()


    }

}