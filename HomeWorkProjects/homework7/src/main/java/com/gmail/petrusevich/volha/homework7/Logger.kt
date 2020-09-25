package com.gmail.petrusevich.volha.homework7

import android.content.Intent
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

private const val FILE_NAME = "MyLogger.txt"
private const val DATE_FORMAT = "yyyy/MM/dd_HH:mm"

class Logger {

    private val fileDirCreate = FileDirCreate.getInstance()
    private val fileDir = fileDirCreate.fileDir
    private val file = File(fileDir, FILE_NAME)

    fun writeLog(text: String?) {
        file.createNewFile()
        val thread = Thread(Runnable {
            if (text != null) {
                file.appendText(text)
            }
        })
        thread.start()
    }


    fun createText(date: Date, intent: Intent?): String {
        val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.US)
        val dateText = dateFormatter.format(date)
        return "$dateText - ${intent?.action.toString()}"
    }


}