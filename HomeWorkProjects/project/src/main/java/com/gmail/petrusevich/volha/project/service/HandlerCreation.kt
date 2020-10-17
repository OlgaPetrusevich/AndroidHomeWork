package com.gmail.petrusevich.volha.project.service

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button

class HandlerCreation(
        val view: Button?
): Handler(Looper.getMainLooper()) {


    override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                view?.text = msg.arg1.toString()
            }
    }

    companion object {
        fun getInstance(view: Button?) = HandlerCreation(view)
    }



}

