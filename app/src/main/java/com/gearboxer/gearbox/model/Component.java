package com.gearboxer.gearbox.model;

/**
 * Created by mbarlow on 26/07/2014.
 */
public class Component {
    private int iconResource;
    private String contents;

    public Component(int iconResource, String contents) {
        this.iconResource = iconResource;
        this.contents = contents;
    }

    public int getIconResource() {
        return iconResource;
    }

    public String getContents() {
        return contents;
    }
}
