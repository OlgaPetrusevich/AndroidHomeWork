package com.gmail.petrusevich.volha.homework7

import android.app.Service
import android.content.Intent
import android.os.IBinder

class SystemService : Service() {

    private val logger = Logger()

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val text = intent?.getStringExtra("KEY")
        logger.writeLog(intent?.action.toString())
        return super.onStartCommand(intent, flags, startId)



    }


}