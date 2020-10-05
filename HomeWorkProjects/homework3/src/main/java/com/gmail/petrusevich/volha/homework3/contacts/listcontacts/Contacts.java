package com.gmail.petrusevich.volha.homework3.contacts.listcontacts;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable {

    private String name;
    private String contactData;
    private transient int image;

    public Contacts(String name, String contactData, int image) {
        this.name = name;
        this.contactData = contactData;
        this.image = image;
    }

    public Contacts(Parcel in) {
        String[] array = new String[2];
        in.readStringArray(array);
        name = array[0];
        contactData = array[1];
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getContactData() {
        return contactData;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{name, contactData});
    }


}
