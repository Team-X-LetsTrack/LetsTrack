package com.example.aranyak.Application;

/**
 * Created by Aranyak on 19-Oct-17.
 */

public abstract class Location {

    protected double latitude;
    protected double longitude;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
