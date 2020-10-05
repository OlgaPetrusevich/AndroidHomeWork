package com.gmail.petrusevich.volha.homework3.contacts.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.petrusevich.volha.homework3.R;
import com.gmail.petrusevich.volha.homework3.contacts.listcontacts.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> implements Filterable {

    private List<Contacts> listContacts;
    private OnContactListener contactListener;
    private List<Contacts> listContactsCopy;

    public ContactListAdapter(List<Contacts> listContacts, OnContactListener onContactListener) {
        this.listContacts = listContacts;
        contactListener = onContactListener;
        listContactsCopy = new ArrayList<>(listContacts);
    }

    public void setListContactsCopy(List<Contacts> listContactsCopy) {
        this.listContactsCopy = new ArrayList<>(listContactsCopy);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view, contactListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return listContacts != null ? listContacts.size() : 0;
    }

    public interface OnContactListener {
        void onContactClick(int position);
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    private Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence text) {
            List<Contacts> listContactsFiltered = new ArrayList<>();
            if (text == null || text.length() == 0) {
                listContactsFiltered.addAll(listContactsCopy);
            } else {
                String editText = text.toString().toLowerCase().trim();
                for (Contacts contact : listContactsCopy) {
                    if (contact.getName().toLowerCase().contains(editText)) {
                        listContactsFiltered.add(contact);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listContactsFiltered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listContacts.clear();
            listContacts.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textName;
        private TextView textContactData;
        private OnContactListener onContactListener;


        public ViewHolder(@NonNull View itemView, OnContactListener onContactListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item);
            textName = itemView.findViewById(R.id.text_name);
            textContactData = itemView.findViewById(R.id.text_contact_data);
            itemView.setOnClickListener(this);
            this.onContactListener = onContactListener;
        }

        public void bind(Contacts contacts) {
            imageView.setImageResource(contacts.getImage());
            textName.setText(contacts.getName());
            textContactData.setText(contacts.getContactData());
        }

        @Override
        public void onClick(View view) {
            onContactListener.onContactClick(getAdapterPosition());
        }
    }
}


