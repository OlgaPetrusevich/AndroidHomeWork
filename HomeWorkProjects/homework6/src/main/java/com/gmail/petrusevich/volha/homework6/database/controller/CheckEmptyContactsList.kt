package com.gmail.petrusevich.volha.homework6.database.controller

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter

fun isEmpty(contactListAdapter: ContactListAdapter, recyclerView: RecyclerView, view: TextView) {
    val itemCount = contactListAdapter.itemCount
    if (itemCount == 0) {
        recyclerView.visibility = View.GONE
        view.visibility = View.VISIBLE
    } else {
        recyclerView.visibility = View.VISIBLE
        view.visibility = View.GONE
    }
}