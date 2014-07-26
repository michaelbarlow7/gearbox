package com.gearboxer.gearbox.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mbarlow on 26/07/2014.
 */
public class GearLocation implements Serializable{
    private float latitude;
    private float longitude;
    private String name;
    private List<Gear> gearList;


    public GearLocation(float latitude, float longitude, String name, List<Gear> gearList) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.gearList = gearList;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public List<Gear> getGearList() {
        return gearList;
    }
}
