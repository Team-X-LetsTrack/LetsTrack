package com.example.aranyak.letstrack;

/**
 * Created by Aranyak on 19-Oct-17.
 */

public abstract class User {
    protected String Email_ID;
    protected String Contact_Number;

    public User(String email_ID, String contact_Number) {
        Email_ID = email_ID;
        Contact_Number = contact_Number;
    }


}
