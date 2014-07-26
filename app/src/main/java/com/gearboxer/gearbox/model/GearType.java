package com.gearboxer.gearbox.model;

import com.gearboxer.gearbox.R;

/**
 * Created by mbarlow on 26/07/2014.
 */

public enum GearType {
    VOLLEYBALL("Volleyball", R.drawable.volleyball, new Component(R.drawable.volleyball, "2 Volleyballs")),
    TENNIS("Tennis", R.drawable.tennis_ball, new Component(0, "4 tennisballs"), new Component(R.drawable.tennis_racquet, "4 racquets")),
    SOCCER("Soccer", R.drawable.soccer_ball, new Component(R.drawable.soccer_ball, "1 soccerball")),
    BASKETBALL("Basketball", R.drawable.basketball, new Component(R.drawable.basketball, "1 basketball")),
    BEACHBALL("Beachball", R.drawable.beach_ball, new Component(R.drawable.beach_ball, "1 beachball")),
    CRICKET("Cricket", R.drawable.cricket_ball, new Component(R.drawable.cricket_bat, "1 bat"), new Component(R.drawable.cricket_bat, "2 wickets"), new Component(R.drawable.cricket_ball, "1 ball"));
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
