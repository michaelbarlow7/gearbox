package com.gearboxer.gearbox.model;

/**
 * Created by mbarlow on 26/07/2014.
 */

public enum GearType {
    VOLLEYBALL("Volleyball", 0, new Component(0, "2 Volleyballs")),
    TENNIS("Tennis", 0, new Component(0, "4 tennisballs"), new Component(0, "4 racquets")),
    SOCCER("Soccer", 0, new Component(0, "1 soccerball")),
    BASKETBALL("Basketball", 0, new Component(0, "1 basketball")),
    BEACHBALL("Beachball", 0, new Component(0, "1 beachball")),
    CRICKET("Cricket", 0, new Component(0, "1 bat"), new Component(0, "2 wickets"), new Component(0, "1 ball"));
    private String name;
    private int iconResource;
    private Component[] components;

    GearType(String name, int iconResource, Component... components) {
        this.name = name;
        this.iconResource = iconResource;
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public int getIconResource() {
        return iconResource;
    }

    public Component[] getComponents() {
        return components;
    }
}
