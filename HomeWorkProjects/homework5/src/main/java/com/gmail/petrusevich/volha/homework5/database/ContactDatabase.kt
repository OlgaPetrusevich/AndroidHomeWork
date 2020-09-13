package com.gmail.petrusevich.volha.homework5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun getContactDao(): ContactDao

    companion object {
        var instance: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, ContactDatabase::class.java, "ContactsNew")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }


}