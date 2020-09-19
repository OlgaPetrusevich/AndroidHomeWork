package com.gmail.petrusevich.volha.homework6.database.controller

import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter

class SearchContact {

    fun searchContact(searchView: androidx.appcompat.widget.SearchView, adapter: ContactListAdapter, repository: Repository) {
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val text = "%$newText%"
                repository.getSearchContacts(text)
                return false
            }
        })
    }


}