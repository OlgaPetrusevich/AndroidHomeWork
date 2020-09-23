package com.gmail.petrusevich.volha.homework5

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.homework5.adapter.ContactListAdapter
import com.gmail.petrusevich.volha.homework5.database.controller.ContactViewModel
import com.gmail.petrusevich.volha.homework5.adapter.OnContactListener
import com.gmail.petrusevich.volha.homework5.database.ContactDatabase
import com.gmail.petrusevich.volha.homework5.database.Contacts
import com.gmail.petrusevich.volha.homework5.database.controller.SearchContact
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, OnContactListener {

    private val listContacts: List<Contacts> = mutableListOf()
    private lateinit var contactDatabase: ContactDatabase
    private lateinit var viewModel: ContactViewModel
    private val searchContact: SearchContact = SearchContact()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAddButton.setOnClickListener(this)
        contactDatabase = ContactDatabase.getInstance(this)!!
        recyclerView = findViewById<RecyclerView>(R.id.viewListContact)
        val contactListAdapter = ContactListAdapter(listContacts, this)
        recyclerView.adapter = contactListAdapter
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        viewModel.listContacts?.observe(this, Observer {
            it.let {
                contactListAdapter.updateListContact(it)
                if (it.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    viewNoContactText.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    viewNoContactText.visibility = View.GONE
                }
            }
        })
        searchContact.searchContact(viewSearchContact, contactListAdapter, viewModel)
    }


    override fun onClick(view: View?) {
        if (view == viewAddButton) {
            startActivity(AddContactActivity.newIntent(this))
        }
    }

    override fun onContactClick(position: Int) {
        val contacts = viewModel.listContacts?.value
        startActivity(EditContactActivity.newIntent(this, contacts?.get(position)?.id))
    }

}
