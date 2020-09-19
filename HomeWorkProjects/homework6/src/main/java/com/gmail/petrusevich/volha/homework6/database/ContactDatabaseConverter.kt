package com.gmail.petrusevich.volha.homework6.database

import androidx.room.TypeConverter

class ContactDatabaseConverter {

    @TypeConverter
    fun dataTypeToString(dataType: ContactDataType): String = dataType.name

    @TypeConverter
    fun stringToDataType(dataType: String): ContactDataType = ContactDataType.valueOf(dataType)

}