package com.gmail.petrusevich.volha.homework7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val broadcastReceiverController by lazy { BroadcastReceiverController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val systemBroadcastReceiver = SystemBroadcastReceiver()
        registerReceiver(systemBroadcastReceiver, broadcastReceiverController.createBroadcastReceiver())
        val fileDirCreate = FileDirCreate.getInstance()
        fileDirCreate.getFileDir(this, StorageType.INTERNAL)
        startService(Intent(this, SystemService::class.java))
    }

}
