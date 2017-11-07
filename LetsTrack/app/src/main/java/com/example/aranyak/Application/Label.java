package com.example.aranyak.Application;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Aranyak on 19-Oct-17.
 */

public class Label {


    private String Label_name;
    private LatLng location;

    public void setLabel_name(String label_name) {
        Label_name = label_name;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
