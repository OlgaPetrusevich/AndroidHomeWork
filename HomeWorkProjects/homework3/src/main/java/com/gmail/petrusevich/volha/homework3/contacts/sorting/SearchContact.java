package com.gmail.petrusevich.volha.homework3.contacts.sorting;

import com.gmail.petrusevich.volha.homework3.contacts.adapter.ContactListAdapter;

public class SearchContact {

    private SearchContact() {
    }

    public static void searchContact(androidx.appcompat.widget.SearchView searchView, final ContactListAdapter contactListAdapter) {
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactListAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

}
