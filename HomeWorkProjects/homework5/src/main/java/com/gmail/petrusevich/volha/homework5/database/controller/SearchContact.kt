package com.gmail.petrusevich.volha.homework5.database.controller

import com.gmail.petrusevich.volha.homework5.adapter.ContactListAdapter
import com.gmail.petrusevich.volha.homework5.database.controller.ContactViewModel

class SearchContact {

    fun searchContact(searchView: androidx.appcompat.widget.SearchView, adapter: ContactListAdapter, viewModel: ContactViewModel) {
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val text = "%$newText%"
                val list = viewModel.getSearchContact(text)
                if (list != null) {
                    adapter.updateListContact(list)
                }
                return false
            }
        })
    }


}