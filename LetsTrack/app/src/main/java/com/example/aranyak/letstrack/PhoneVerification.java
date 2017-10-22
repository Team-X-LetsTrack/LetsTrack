package com.example.aranyak.letstrack;

import android.telephony.SmsManager;

import java.util.Random;

/**
 * Created by Aranyak on 21-Oct-17.
 */

public class PhoneVerification {

    private String phone;

    private String code;

    public PhoneVerification(String phone) {
        this.phone = phone;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void sendVerificationtext() {
        Random rn = new Random();
        code = Integer.toString(100000 + rn.nextInt(100000));
        String message = "Your verification code is " + code + ".";
        SmsManager sm = SmsManager.getDefault();
        sm.sendTextMessage(phone, null, message, null, null);
    }

    public String getCode() {
        return code;
    }

    public boolean verifyCode(String c) {

        if (c == code)
            return true;
        else
            return false;
    }
}
