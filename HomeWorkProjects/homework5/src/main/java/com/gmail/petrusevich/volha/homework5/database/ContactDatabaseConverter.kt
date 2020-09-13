package com.gmail.petrusevich.volha.homework5.database

import androidx.room.TypeConverter
import com.gmail.petrusevich.volha.homework5.database.ContactDataType

class ContactDatabaseConverter {

    @TypeConverter
    fun dataTypeToString(dataType: ContactDataType): String = dataType.name

    @TypeConverter
    fun stringToDataType(dataType: String): ContactDataType = ContactDataType.valueOf(dataType)

}