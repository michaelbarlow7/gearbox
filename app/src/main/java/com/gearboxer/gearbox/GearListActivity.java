package com.gearboxer.gearbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gearboxer.gearbox.adapter.ComponentListAdapter;
import com.gearboxer.gearbox.adapter.GearListAdapter;
import com.gearboxer.gearbox.model.Gear;
import com.gearboxer.gearbox.model.GearLocation;

public class GearListActivity extends Activity {
    public static String LOCATION_EXTRA = "LOCATION_EXTRA";
    private GearLocation gearLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gear_list);

        gearLocation = (GearLocation) getIntent().getSerializableExtra(LOCATION_EXTRA);
        if (gearLocation == null){
            finish();
            return;
        }

        TextView titleView = (TextView) findViewById(R.id.titleText);
        titleView.setText(gearLocation.getName());

        // Populate listview
        ListView gearListView = (ListView) findViewById(R.id.gearListView);
        GearListAdapter adapter = new GearListAdapter(gearLocation.getGearList());
        gearListView.setAdapter(adapter);
        gearListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gear gear = (Gear) parent.getItemAtPosition(position);

                // Launch dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle(gear.getGearType().getName());
                View dialogView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_gear, null);

                ListView componentListView = (ListView) dialogView.findViewById(R.id.componentListView);
                ComponentListAdapter adapter = new ComponentListAdapter(gear.getGearType().getComponents());
                componentListView.setAdapter(adapter);

                builder.setView(dialogView);

                // On click goes here
                builder.setPositiveButton("Grab gear", null);

                builder.show();
            }
        });

    }

}
