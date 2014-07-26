package com.gearboxer.gearbox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.gearboxer.gearbox.adapter.GearListAdapter;
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
    }

}
