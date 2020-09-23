package com.gmail.petrusevich.volha.homework6.database.controller.workthreads;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.petrusevich.volha.homework6.R;
import com.gmail.petrusevich.volha.homework6.database.controller.CheckEmptyContactsListKt;
import com.gmail.petrusevich.volha.homework6.database.datacontact.ContactInfo;
import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter;
import com.gmail.petrusevich.volha.homework6.database.ContactDao;
import com.gmail.petrusevich.volha.homework6.database.datacontact.Contacts;
import com.gmail.petrusevich.volha.homework6.database.controller.Repository;
import com.gmail.petrusevich.volha.homework6.database.controller.SortedList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ThreadWithFuture implements Repository {

    private ThreadPoolExecutor threadPoolExecutor;
    private ContactDao contactDao;
    private ContactListAdapter adapter;
    private List<Contacts> listContacts;
    private SortedList sortedList = new SortedList();
    private ContactInfo contactInfo = ContactInfo.getInstance();
    private Context context;
    private RecyclerView recyclerView;
    private TextView viewText;

    public ThreadWithFuture(ContactDao contactDao, ContactListAdapter adapter, List<Contacts> listContacts, Context context, RecyclerView recyclerView) {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        this.contactDao = contactDao;
        this.adapter = adapter;
        this.listContacts = listContacts;
        this.context = context;
        this.recyclerView = recyclerView;
        viewText = ((Activity)context).findViewById(R.id.viewNoContactText);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @NotNull
    @Override
    public List<Contacts> getAllContacts() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<List<Contacts>>() {
            @Override
            public List<Contacts> get() {
                listContacts = contactDao.getAllContacts();
                return listContacts;
            }
        }, threadPoolExecutor)
                .thenAcceptAsync(new Consumer<List<Contacts>>() {
                    @Override
                    public void accept(List<Contacts> list) {
                        adapter.updateListContact(list);
                        CheckEmptyContactsListKt.isEmpty(adapter, recyclerView, viewText);
                    }
                }, context.getMainExecutor());
        return listContacts;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void getContact(final int position) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Contacts>() {
            @Override
            public Contacts get() {
                String idContact = listContacts.get(position).getId();
                return contactDao.getContact(idContact);
            }
        }, threadPoolExecutor)
                .thenAcceptAsync(new Consumer<Contacts>() {
                    @Override
                    public void accept(Contacts contacts) {
                        contactInfo.setNameContact(contacts.getName());
                        contactInfo.setDataContact(contacts.getContactData());
                        contactInfo.setIdContact(contacts.getId());
                    }
                }, context.getMainExecutor());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void getSearchContacts(@Nullable final String search) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<List<Contacts>>() {
            @Override
            public List<Contacts> get() {
                return contactDao.getSearchList(search);
            }
        }, threadPoolExecutor)
                .thenAcceptAsync(new Consumer<List<Contacts>>() {
                    @Override
                    public void accept(List<Contacts> contacts) {
                        adapter.updateListContact(contacts);
                    }
                }, context.getMainExecutor());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void insert(@NotNull final Contacts contact) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                contactDao.insertContact(contact);
            }
        }, threadPoolExecutor)
                .thenAcceptAsync(new Consumer<Void>() {
                    @Override
                    public void accept(Void aVoid) {
                        int position = sortedList.getSortedPosition(listContacts, contact);
                        listContacts.add(position, contact);
                        adapter.updateListContact(listContacts);
                        CheckEmptyContactsListKt.isEmpty(adapter, recyclerView, viewText);
                    }
                }, context.getMainExecutor());
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void delete(@NotNull final String idContact) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                Contacts contact = contactDao.getContact(idContact);
                if (contact != null) {
                    contactDao.deleteContact(contact);
                }
            }
        }, threadPoolExecutor)
                .thenAcceptAsync(new Consumer<Void>() {
                    @Override
                    public void accept(Void aVoid) {
                        listContacts.remove(contactInfo.getPosition());
                        adapter.updateListContact(listContacts);
                        CheckEmptyContactsListKt.isEmpty(adapter, recyclerView, viewText);
                    }
                }, context.getMainExecutor());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void update(@NotNull final String idContact) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                Contacts contact = contactDao.getContact(idContact);
                if (contact != null) {
                    contact.setName(contactInfo.getNameContact());
                    contact.setContactData(contactInfo.getDataContact());
                    contactDao.updateContact(contact);
                }
            }
        }, threadPoolExecutor)
                .thenAcceptAsync(new Consumer<Void>() {
                    @Override
                    public void accept(Void aVoid) {
                        Contacts contactNew = listContacts.get(contactInfo.getPosition());
                        contactNew.setName(contactInfo.getNameContact());
                        contactNew.setContactData(contactInfo.getDataContact());
                        listContacts.remove(contactInfo.getPosition());
                        int position = sortedList.getSortedPosition(listContacts, contactNew);
                        listContacts.add(position, contactNew);
                        adapter.updateListContact(listContacts);
                    }
                }, context.getMainExecutor());
    }

    @Override
    public void closeThreads() {
        threadPoolExecutor.shutdown();
    }
}
