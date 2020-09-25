package com.gmail.petrusevich.volha.homework7.logger

import java.io.File

private const val FILE_NAME = "Logger.txt"

class Logger {

    private val fileDir = FileDirCreate.fileDir
    private val file = File(fileDir, FILE_NAME)

    fun writeLog(text: String?) {
        val thread = Thread(Runnable {
            if (text != null) {
                file.appendText(text)
            }
        })
        thread.start()
    }


}