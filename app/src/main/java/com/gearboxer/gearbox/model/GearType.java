package com.gearboxer.gearbox.model;

/**
 * Created by mbarlow on 26/07/2014.
 */

public enum GearType {
    VOLLEYBALL("Volleyball"),
    TENNIS("Tennis"),
    SOCCER("Soccer"),
    BASKETBALL("Basketball"),
    BEACHBALL("Beachball"),
    CRICKET("Cricket");
    private String name;

    GearType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
