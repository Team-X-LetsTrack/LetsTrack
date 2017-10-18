package com.example.aranyak.letstrack;

/**
 * Created by Aranyak on 19-Oct-17.
 */

public class Label extends Location {

    private String Label_name;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Label(double latitude, double longitude, String label_name) {
        super(latitude, longitude);
        Label_name = label_name;
    }
}
