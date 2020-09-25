package com.gmail.petrusevich.volha.homework7

import android.content.Intent
import android.content.IntentFilter

class BroadcastReceiverController {

    fun createBroadcastReceiver(): IntentFilter{
        return IntentFilter().apply {
            addAction(Intent.ACTION_INSERT)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_SCREEN_ON)
        }
    }


}