package com.gmail.petrusevich.volha.project.service

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button


class TimerController(
        val viewTimerButton: Button
) {

    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                viewTimerButton.text = msg.arg1.toString()
            }
        }
    }

    fun getTimerTime() {
        val thread = Thread(Runnable {
            var time = 2
            while (time != 0) {
                Thread.sleep(1000)
                handler.sendMessage(handler.obtainMessage(1, time))
                time--
            }
        })
        thread.start()
    }

}