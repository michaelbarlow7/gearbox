package com.gearboxer.gearbox.model;

/**
 * Created by mbarlow on 26/07/2014.
 */
public class Gear {
    private GearType gearType;
    private int quantity;

    public Gear(GearType gearType, int quantity){
        this.gearType = gearType;
        this.quantity = quantity;
    }

    public GearType getGearType() {
        return gearType;
    }

    public int getQuantity() {
        return quantity;
    }
}

