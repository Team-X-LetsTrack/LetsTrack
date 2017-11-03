package com.example.aranyak.letstrack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aranyak on 03-Nov-17.
 */

public class UserDatabaseHelper {
    Map<String, Object> user;

    public UserDatabaseHelper() {
        this.user = new HashMap<>();
    }

    public void addUser(Primary_User u) {
        user.put("Email", u.getEmail_ID());
        //user.put("");
    }
}
