package com.gearboxer.gearbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gearboxer.gearbox.model.Gear;
import com.gearboxer.gearbox.model.GearLocation;
import com.gearboxer.gearbox.model.GearType;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapActivity extends Activity {
    public static final String TAG = MapActivity.class.getName();

    private GoogleMap mMap;
    private LatLng myLocation;
    private List<GearLocation> locationList;
    private GearLocationOnInfoWindowClickListener gearLocationOnInfoWindowClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        gearLocationOnInfoWindowClickListener = new GearLocationOnInfoWindowClickListener();

        setUpMapIfNeeded();

        locationList = ((GearApplication) getApplication()).getGearLocationList();
        attachMarkers();

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                int gearLocationPosition = Integer.parseInt(marker.getSnippet());
                GearLocation gearLocation = locationList.get(gearLocationPosition);

                LayoutInflater inflater = LayoutInflater.from(MapActivity.this);
                View view = inflater.inflate(R.layout.marker_info, null);
                TextView text = (TextView) view.findViewById(R.id.markerInfoTitle);
                text.setText(gearLocation.getName());

                List<Gear> gears = gearLocation.getGearList();
                for (Gear gear : gears) {
                    if (gear.getGearType() == GearType.VOLLEYBALL) {
                        View icon = view.findViewById(R.id.volleyball);
                        icon.setVisibility(View.VISIBLE);
                        continue;
                    }
                    if (gear.getGearType() == GearType.TENNIS) {
                        View icon = view.findViewById(R.id.tennis);
                        icon.setVisibility(View.VISIBLE);
                        continue;
                    }
                    if (gear.getGearType() == GearType.SOCCER) {
                        View icon = view.findViewById(R.id.soccer);
                        icon.setVisibility(View.VISIBLE);
                        continue;
                    }
                    if (gear.getGearType() == GearType.BASKETBALL) {
                        View icon = view.findViewById(R.id.basketball);
                        icon.setVisibility(View.VISIBLE);
                        continue;
                    }
                    if (gear.getGearType() == GearType.BEACHBALL) {
                        View icon = view.findViewById(R.id.beachball);
                        icon.setVisibility(View.VISIBLE);
                        continue;
                    }
                    if (gear.getGearType() == GearType.CRICKET) {
                        View icon = view.findViewById(R.id.cricket);
                        icon.setVisibility(View.VISIBLE);
                        continue;
                    }
                }

                return view;
            }
        });
        mMap.setOnInfoWindowClickListener(gearLocationOnInfoWindowClickListener);
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

    private void attachMarkers() {
        int i = 0;
        for (GearLocation gearLocation : locationList) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(gearLocation.getLatitude(), gearLocation.getLongitude()))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .snippet(String.valueOf(i))
                    .title(gearLocation.getName()));
            i++;
        }
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

    private class GearLocationOnInfoWindowClickListener implements GoogleMap.OnInfoWindowClickListener {

        public GearLocationOnInfoWindowClickListener() {
        }

        @Override
        public void onInfoWindowClick(Marker marker) {
            int gearLocationPosition = Integer.parseInt(marker.getSnippet());
            GearLocation gearLocation = locationList.get(gearLocationPosition);

            Intent intent = new Intent(MapActivity.this, GearListActivity.class);
            intent.putExtra(GearListActivity.LOCATION_EXTRA, gearLocation);
            startActivity(intent);
        }
    }
}
