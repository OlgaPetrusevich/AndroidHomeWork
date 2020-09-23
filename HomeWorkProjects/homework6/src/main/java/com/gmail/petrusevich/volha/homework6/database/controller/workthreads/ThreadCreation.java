package com.gmail.petrusevich.volha.homework6.database.controller.workthreads;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter;
import com.gmail.petrusevich.volha.homework6.database.ContactDao;
import com.gmail.petrusevich.volha.homework6.database.datacontact.Contacts;
import com.gmail.petrusevich.volha.homework6.database.controller.Repository;

import java.util.List;

public class ThreadCreation {

    public Repository createThreadWork(int position, ContactDao contactDao,
                                       ContactListAdapter adapter, List<Contacts> list, Context context, RecyclerView recyclerView) {
        Repository repository = null;
        switch (position) {
            case 0:
                repository = new ThreadWithHandler(contactDao, adapter, list, recyclerView, context);
                break;
            case 1:
                repository = new ThreadWithFuture(contactDao, adapter, list, context, recyclerView);
                break;
            case 2:
                repository = new ThreadWithRxJava(contactDao, adapter, list, recyclerView, context);
        }
        return repository;
    }
}
