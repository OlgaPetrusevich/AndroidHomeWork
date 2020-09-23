package com.gmail.petrusevich.volha.homework6.database

import androidx.room.TypeConverter
import com.gmail.petrusevich.volha.homework6.database.datacontact.ContactDataType

class ContactDatabaseConverter {

    @TypeConverter
    fun dataTypeToString(dataType: ContactDataType): String = dataType.name

    @TypeConverter
    fun stringToDataType(dataType: String): ContactDataType = ContactDataType.valueOf(dataType)

}