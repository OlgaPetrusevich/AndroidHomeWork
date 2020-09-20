package com.gmail.petrusevich.volha.homework6.database.controller

import com.gmail.petrusevich.volha.homework6.database.datacontact.Contacts

interface Repository {

    fun getAllContacts(): List<Contacts>

    fun getContact(position: Int)

    fun getSearchContacts(search: String?)

    fun insert(contact: Contacts)

    fun delete(idContact: String)

    fun update(idContact: String)

    fun closeThreads()

}