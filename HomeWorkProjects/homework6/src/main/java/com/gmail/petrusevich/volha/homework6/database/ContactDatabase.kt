package com.gmail.petrusevich.volha.homework6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.petrusevich.volha.homework6.database.datacontact.Contacts

@Database(entities = [Contacts::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun getContactDao(): ContactDao

    companion object {
        var instance: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, ContactDatabase::class.java, "MyContacts")
                        .build()
            }
            return instance
        }
    }


}