package com.example.aranyak.letstrack;

/**
 * Created by Aranyak on 19-Oct-17.
 */

public abstract class Location {

    protected double latitude;
    protected double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
