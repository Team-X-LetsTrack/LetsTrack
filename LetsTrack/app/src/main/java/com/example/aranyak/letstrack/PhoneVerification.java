package com.example.aranyak.letstrack;

import android.telephony.SmsManager;

import java.util.Random;

/**
 * Created by Aranyak on 21-Oct-17.
 */

public class PhoneVerification {

    private String phone;

    private long code;

    public PhoneVerification(String phone) {
        this.phone = phone;
    }

    public void sendVerificationtext() {
        Random rn = new Random();
        code = rn.nextInt(999999);
        String message = "Your verification code is " + code + ".";
        SmsManager sm = SmsManager.getDefault();
        sm.sendTextMessage(phone, null, message, null, null);
    }

}
