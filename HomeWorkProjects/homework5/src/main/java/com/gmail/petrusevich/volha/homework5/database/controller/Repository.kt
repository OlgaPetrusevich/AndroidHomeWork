package com.gmail.petrusevich.volha.homework5.database.controller

import androidx.lifecycle.LiveData
import com.gmail.petrusevich.volha.homework5.database.Contacts

interface Repository {

    fun getAllContacts(): LiveData<List<Contacts>>?

    fun getContact(idContact: Int): Contacts?

    fun getSearchContacts(search: String?): List<Contacts>?

    fun insert(contact: Contacts?)

    fun delete(contact: Contacts?)

    fun update(contact: Contacts?)

}