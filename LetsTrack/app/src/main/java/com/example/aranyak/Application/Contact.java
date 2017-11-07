package com.example.aranyak.Application;

/**
 * Created by Aranyak on 19-Oct-17.
 */

public class Contact extends User {

    private String Current_Label;
    private String userName;
    private Status Contact_status;

    public Contact(String email_ID, String contact_Number, String userName) {
        super(email_ID, contact_Number);
        this.userName = userName;
    }

    public void setCurrent_Label(String current_Label) {
        Current_Label = current_Label;
    }

    public void setContact_status(Status contact_status) {
        Contact_status = contact_status;
    }

    public String getCurrent_Label() {
        return Current_Label;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }
}
