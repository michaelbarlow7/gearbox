package com.gearboxer.gearbox;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gearboxer.gearbox.R;
import com.gearboxer.gearbox.model.Gear;
import com.gearboxer.gearbox.model.GearLocation;

public class CurrentlyPlaying extends Activity {
    public static final String LOCATION_EXTRA = "LOCATION_EXTRA";
    public static final String GEAR_EXTRA = "GEAR_EXTRA";
    public static final int SECONDS_TIL_NOTIFY = 10;

    private GearLocation gearLocation;
    private Gear gear;
    private TextView timerText;
    private int time = 58;
    private volatile boolean keepTicking = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_playing);

        gearLocation  = (GearLocation) getIntent().getSerializableExtra(LOCATION_EXTRA);
        gear = (Gear) getIntent().getSerializableExtra(GEAR_EXTRA);

        TextView gearText = (TextView) findViewById(R.id.gearText);
        gearText.setText(gear.getGearType().getName());
        //TODO: Set drawableLeft

        TextView locationText = (TextView) findViewById(R.id.locationText);
        locationText.setText("in " + gearLocation.getName());

        timerText = (TextView) findViewById(R.id.timer);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(keepTicking){
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (keepTicking){
                        runOnUiThread(new TimeRunnable());
                    }
                }
            }
        });
        thread.start();
    }
    private int tickedSeconds = 0;

    public class TimeRunnable implements Runnable{

        @Override
        public void run() {
            timerText.setText(String.format("01:59:%02d", time));
            time--;
            if (time < 0){
                time = 59;
            }
            tickedSeconds++;
            if (tickedSeconds == SECONDS_TIL_NOTIFY){
                showNotification();
            }
        }
    }

    private void showNotification() {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("You have 10 minutes to return your gear!")
                        .setTicker("You have 10 minutes to return your gear!")
                        .setContentText("Please return to the gearbox to return your gear")
                        .setSmallIcon(android.R.drawable.ic_delete)
                        .setSound(soundUri)
                        .setAutoCancel(true)
                        .setOngoing(false);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        keepTicking = false;
    }

    public void onLockClick(View v){
        // Send lock
    }
}
