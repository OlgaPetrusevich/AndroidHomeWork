package com.gmail.petrusevich.volha.homework7.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter

class BroadcastReceiverController {

    fun createBroadcastReceiver(): IntentFilter {
        return IntentFilter().apply {
            addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
    }


}