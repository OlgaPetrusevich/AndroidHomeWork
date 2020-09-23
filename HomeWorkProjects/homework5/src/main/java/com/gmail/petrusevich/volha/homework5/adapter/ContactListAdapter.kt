package com.gmail.petrusevich.volha.homework5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.homework5.R
import com.gmail.petrusevich.volha.homework5.database.Contacts
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactListAdapter(
        private var listContacts: List<Contacts> = mutableListOf(),
        private val onContactListener: OnContactListener) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view, onContactListener)
    }

    override fun getItemCount(): Int {
        return listContacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listContacts[position])
    }

    fun updateListContact(contacts: List<Contacts>?) {
        if (contacts != null) {
            listContacts = contacts
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View, private val onContactListener: OnContactListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(contact: Contacts?) {
            itemView.viewTextName.text = contact?.name
            itemView.viewContactData.text = contact?.contactData
            itemView.viewImage.setImageResource(contact?.image!!)
        }

        override fun onClick(view: View?) {
            onContactListener.onContactClick(adapterPosition)
        }
    }

}