package com.gearboxer.gearbox;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends Activity {
    public static final String TAG = MapActivity.class.getName();

    private GoogleMap mMap;
    private LatLng myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setUpMapIfNeeded();

//        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//
//            @Override
//            public void onMyLocationChange(Location location) {
//                Log.d(TAG, "my location change, lat: " + location.getLatitude() + ", long:  " + location.getLongitude());
//                if (myLocation != null){
//                    // Already have location
//                    return;
//                }
//
//                if (location != null) {
//                    myLocation = new LatLng(location.getLatitude(),
//                            location.getLongitude());
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15.0f));
//                }
//            }
//        });
        mMap.setMyLocationEnabled(true);


    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                // The Map is verified. It is now safe to manipulate the map.

            }
        }
    }
}
