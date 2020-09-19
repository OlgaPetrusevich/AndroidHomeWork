package com.gmail.petrusevich.volha.homework6;

public class ContactInfo {

    private static ContactInfo instance;
    private String nameContact;
    private String dataContact;
    private String idContact;
    private int position;

    private ContactInfo() {

    }

    public static ContactInfo getInstance() {
        if (instance == null) {
            instance = new ContactInfo();
        }
        return instance;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    public String getNameContact() {
        return nameContact;
    }

    public String getDataContact() {
        return dataContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public void setDataContact(String dataContact) {
        this.dataContact = dataContact;
    }
}
