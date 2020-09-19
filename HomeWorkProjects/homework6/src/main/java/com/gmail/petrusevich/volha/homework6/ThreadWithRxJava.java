package com.gmail.petrusevich.volha.homework6;

import android.content.Context;

import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter;
import com.gmail.petrusevich.volha.homework6.database.ContactDao;
import com.gmail.petrusevich.volha.homework6.database.Contacts;
import com.gmail.petrusevich.volha.homework6.database.controller.Repository;
import com.gmail.petrusevich.volha.homework6.database.controller.SortedList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThreadWithRxJava implements Repository {

    private ContactDao contactDao;
    private ContactListAdapter adapter;
    private List<Contacts> listContacts;
    private SortedList sortedList = new SortedList();
    private ContactInfo contactInfo = ContactInfo.getInstance();
    private Context context;

    public ThreadWithRxJava(ContactDao contactDao, ContactListAdapter adapter, List<Contacts> listContacts, Context context) {
        this.contactDao = contactDao;
        this.adapter = adapter;
        this.listContacts = listContacts;
        this.context = context;
    }

    @NotNull
    @Override
    public List<Contacts> getAllContacts() {
        return null;
    }

    @Override
    public void getContact(int position) {

    }

    @Override
    public void getSearchContacts(@Nullable String search) {

    }

    @Override
    public void insert(@NotNull Contacts contact) {

    }

    @Override
    public void delete(@NotNull String idContact) {

    }

    @Override
    public void update(@NotNull String idContact) {

    }
}
