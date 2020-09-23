package com.gmail.petrusevich.volha.homework5.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
@TypeConverters(ContactDatabaseConverter::class)
interface ContactDao {

    @Query("SELECT * FROM Contacts ORDER BY name COLLATE NOCASE ASC")
    fun getAllContacts(): LiveData<List<Contacts>>?

    @Query("SELECT * FROM Contacts ORDER BY name COLLATE NOCASE ASC")
    fun getAll(): List<Contacts>?

    @Query("SELECT * FROM Contacts WHERE id = :idContact")
    fun getContact(idContact: Int): Contacts?

    @Query("SELECT * FROM Contacts WHERE name LIKE :search ORDER BY name COLLATE NOCASE ASC")
    fun getSearchList(search: String?): List<Contacts>?

    @Insert
    fun insertContact(contact: Contacts?)

    @Update
    fun updateContact(contact: Contacts?)

    @Delete
    fun deleteContact(contact: Contacts?)
}