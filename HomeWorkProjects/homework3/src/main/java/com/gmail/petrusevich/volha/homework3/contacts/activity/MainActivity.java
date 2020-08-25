package com.gmail.petrusevich.volha.homework3.contacts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.petrusevich.volha.homework3.R;
import com.gmail.petrusevich.volha.homework3.contacts.adapter.ContactListAdapter;
import com.gmail.petrusevich.volha.homework3.contacts.editing.EditListContacts;
import com.gmail.petrusevich.volha.homework3.contacts.listcontacts.Contacts;
import com.gmail.petrusevich.volha.homework3.contacts.sorting.SearchContact;
import com.gmail.petrusevich.volha.homework3.contacts.sorting.SortedContact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ContactListAdapter.OnContactListener {

    private RecyclerView recyclerView;
    private List<Contacts> contacts = new ArrayList<>();
    private FloatingActionButton button;
    private int position;
    private TextView noContact;
    private SortedContact sortedContact = new SortedContact(contacts);
    private SearchView searchView;
    private SearchContact searchContact = new SearchContact();
    private ContactListAdapter contactListAdapter;
    private EditListContacts editListContacts = new EditListContacts();
    private final String SAVE_KEY = "saveListContacts";
    private final String SAVE_SEARCH_KEY = "saveSearchContacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        button.setOnClickListener(this);
        sortedContact.sortContact(contacts);
        contactListAdapter = new ContactListAdapter(contacts, this);
        recyclerView.setAdapter(contactListAdapter);
        emptyList();
        searchContact.searchContact(searchView, contactListAdapter);
    }

    private int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_button) {
            startActivityForResult(AddContactActivity.newIntent(this), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        } else if (requestCode == 1) {
            editListContacts.addContact(data, contacts, contactListAdapter);
            emptyList();
        } else if (requestCode == 2) {
            editListContacts.editContact(data, contacts, contactListAdapter, getPosition());
            editListContacts.removeContact(data, contacts, contactListAdapter, position);
            emptyList();
        }
    }

    @Override
    public void onContactClick(int position) {
        setPosition(position);
        startActivityForResult(EditContactActivity.newIntent(this, contacts.get(position)), 2);
    }

    private void emptyList() {
        if (contacts.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            noContact.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noContact.setVisibility(View.GONE);
        }
    }

    private void findView() {
        recyclerView = findViewById(R.id.list_contact);
        button = findViewById(R.id.add_button);
        noContact = findViewById(R.id.noContactText);
        searchView = findViewById(R.id.search_contact);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_SEARCH_KEY, searchView.getQuery().toString());
        outState.putParcelableArrayList(SAVE_KEY, (ArrayList<Contacts>) contacts);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contacts = savedInstanceState.getParcelableArrayList(SAVE_KEY);
        contactListAdapter = new ContactListAdapter(contacts, this);
        recyclerView.setAdapter(contactListAdapter);
        searchContact.searchContact(searchView, contactListAdapter);
        emptyList();
        String t = savedInstanceState.getString(SAVE_SEARCH_KEY);
        searchView.setQuery(t, true);
    }

}
