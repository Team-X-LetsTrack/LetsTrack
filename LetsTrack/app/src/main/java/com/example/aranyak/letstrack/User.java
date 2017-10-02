package com.example.aranyak.letstrack;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by arany on 03-Oct-17.
 */

public class User extends AsyncTask<Void, Void, Void> {
    private String email, contact_no;

    private ArrayList<String> Contacts;

    public User(String email, String contact_no) {
        this.email = email;
        this.contact_no = contact_no;
        Contacts = new ArrayList<>();
    }

    private double home, work, gym, vacation;   //Find suitable data types

    public void addContact(String email) {
        Contacts.add(email);
        //Update field in database
    }

    public void registerUser() {
        //Push user to database

    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
}
