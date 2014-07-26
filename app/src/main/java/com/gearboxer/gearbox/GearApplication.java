package com.gearboxer.gearbox;

import android.app.Application;

import com.gearboxer.gearbox.model.Gear;
import com.gearboxer.gearbox.model.GearLocation;
import com.gearboxer.gearbox.model.GearType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbarlow on 26/07/2014.
 */
public class GearApplication extends Application {

    private List<GearLocation> gearLocationList;

    @Override
    public void onCreate() {
        super.onCreate();

        gearLocationList = new ArrayList<GearLocation>();
        addLocation("Hyde Park", -33.87500f, 151.2110000f, 1, 2, 0, 0, 0, 0);
        addLocation("Not Hyde Park", -33.86500f, 151.2117000f, 1, 0, 2, 0, 1, 5);
    }

    private void addLocation(String locationName, float latitude, float longtitude, int volleyballs, int tennisballs, int soccerBalls, int basketballs, int beachballs, int cricketballs) {
        List<Gear> gearList = new ArrayList<Gear>();
        if (volleyballs > 0) {
            gearList.add(new Gear(GearType.VOLLEYBALL, volleyballs));
        }
        if (tennisballs > 0) {
            gearList.add(new Gear(GearType.TENNIS, tennisballs));
        }
        if (soccerBalls > 0) {
            gearList.add(new Gear(GearType.SOCCER, soccerBalls));
        }
        if (basketballs > 0) {
            gearList.add(new Gear(GearType.BASKETBALL, basketballs));
        }
        if (beachballs > 0) {
            gearList.add(new Gear(GearType.BEACHBALL, beachballs));
        }
        if (cricketballs > 0) {
            gearList.add(new Gear(GearType.CRICKET, cricketballs));
        }

        GearLocation gearLocation = new GearLocation(latitude, longtitude, locationName, gearList);
        gearLocationList.add(gearLocation);
    }

    public List<GearLocation> getGearLocationList(){
        return gearLocationList;
    }
}

