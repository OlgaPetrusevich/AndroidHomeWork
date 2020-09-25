package com.gmail.petrusevich.volha.homework7

import android.content.Context
import android.os.Environment
import java.io.File

class FileDirCreate private constructor() {

    lateinit var fileDir: File

    companion object {
        private var fileDirCreate: FileDirCreate? = null
        fun getInstance(): FileDirCreate {
            if (fileDirCreate == null)
                fileDirCreate = FileDirCreate()
            return FileDirCreate()
        }
    }

    fun getFileDir(context: Context, storageType: StorageType){
        fileDir = context.filesDir
//        if (storageType == StorageType.EXTERNAL) {
//            fileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!
//        }
    }

}