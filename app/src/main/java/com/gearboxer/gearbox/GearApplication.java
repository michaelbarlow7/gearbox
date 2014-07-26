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
//        addLocation("Hyde Park", -33.87500f, 151.2110000f, 1, 2, 0, -1, 0, 0);
//        addLocation("Fragrance Garden", -33.872684f, 151.214012f, 1, -1, 2, 5, 1, 5);
//        addLocation("Royal Botanic Gardens", -33.871205f, 151.215514f, -1, -1, 6, 5, 2, 6);
//        addLocation("Royal Botanic Gardens", -33.867402f, 151.215332f, 4, 5, 6, 0, -1, 0);
//        addLocation("Best Street", -33.871357F, 151.221436F, 2, 0, 4, 0, -1, 0);
//        addLocation("Harmony Park", -33.879783f, 151.221780f, 0, 0, 3, 0, 2, 3);
//        addLocation("Belmore Park", -33.881333f, 151.207746f, 2, -1, 5, 0, 1, 3);
//        addLocation("Wentworth Park", -33.877111f, 151.193370f, 2, -1, 5, 0, 1, 3);
//        addLocation("Rushcutters Bay Park", -33.875526F, 151.231135f, 2, -1, 5, 0, 1, 3);
//        addLocation("Centennial Park", -33.892377f, 151.237530F, 2, -1, 5, 0, 1, 3);
//        addLocation("Moore Park", -33.887996f, 151.220535f, 2, -1, 5, 0, 1, 3);
        //
        addLocation("Bondi Beach", -33.892545f, 151.275063f, 2, -1, 0, -1, 6, 1);
        addLocation("Parklands Sport Centre", -33.896363f, 151.224511f, 0, 1, 1, 2, -1, 3);
        addLocation("Wentworth Park", -33.877500f, 151.193441f, 0, -1, 3, -1, -1, 2);
        addLocation("Reg Bartley Oval", -33.874471f, 151.207388f, 0, -1, 3, -1, -1, 1);
        addLocation("King George V Park", -33.858044f, 151.207388f, 3, 0, -1, 6, -1, -1);
        addLocation("Sydney Tennis Centre", -33.889620f, 151.176220f, -1, 3, 1, 6, -1, -1);
        addLocation("Green Park", -33.879439f, 151.220362f, -1, -1, 1, -1, -1, 5);
        addLocation("Nielsen Park", -33.853515f, 151.266796f, -1, 0, 3, -1, 1, 5);
    }

    private void addLocation(String locationName, float latitude, float longtitude, int volleyballs, int tennisballs, int soccerBalls, int basketballs, int beachballs, int cricketballs) {
        List<Gear> gearList = new ArrayList<Gear>();
        if (volleyballs > -1) {
            gearList.add(new Gear(GearType.VOLLEYBALL, volleyballs));
        }
        if (tennisballs > -1) {
            gearList.add(new Gear(GearType.TENNIS, tennisballs));
        }
        if (soccerBalls > -1) {
            gearList.add(new Gear(GearType.SOCCER, soccerBalls));
        }
        if (basketballs > -1) {
            gearList.add(new Gear(GearType.BASKETBALL, basketballs));
        }
        if (beachballs > -1) {
            gearList.add(new Gear(GearType.BEACHBALL, beachballs));
        }
        if (cricketballs > -1) {
            gearList.add(new Gear(GearType.CRICKET, cricketballs));
        }

        GearLocation gearLocation = new GearLocation(latitude, longtitude, locationName, gearList);
        gearLocationList.add(gearLocation);
    }

    public List<GearLocation> getGearLocationList(){
        return gearLocationList;
    }
}

