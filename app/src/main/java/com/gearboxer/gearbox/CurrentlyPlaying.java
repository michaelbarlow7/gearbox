package com.gearboxer.gearbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gearboxer.gearbox.model.Gear;
import com.gearboxer.gearbox.model.GearLocation;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class CurrentlyPlaying extends Activity {
    public static final String LOCATION_EXTRA = "LOCATION_EXTRA";
    public static final String GEAR_EXTRA = "GEAR_EXTRA";
    public static final int SECONDS_TIL_NOTIFY = 10;
    private static final String TAG = CurrentlyPlaying.class.getName();

    private GearLocation gearLocation;
    private Gear gear;
    private TextView timerText;
    private int time = 58;
    private volatile boolean keepTicking = true;
    private ProgressDialog progressDialog;

    private boolean open = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_playing);

        gearLocation  = (GearLocation) getIntent().getSerializableExtra(LOCATION_EXTRA);
        gear = (Gear) getIntent().getSerializableExtra(GEAR_EXTRA);

        ImageView splashGear = (ImageView) findViewById(R.id.splashGear);
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        splashGear.startAnimation(rotateAnimation);

        TextView gearText = (TextView) findViewById(R.id.gearText);
        gearText.setText(gear.getGearType().getName());
        gearText.setText(gear.getGearType().getName() + " in " + gearLocation.getName());
//        gearText.setCompoundDrawablesWithIntrinsicBounds(gear.getGearType().getIconResource(), 0, 0, 0);
//        TextView isPlayingText = (TextView) findViewById(R.id.isPlayingText);
//        isPlayingText.setText(gear.getGearType().getName() + " in " + gearLocation.getName());

//        TextView locationText = (TextView) findViewById(R.id.locationText);
//        locationText.setText("in " + gearLocation.getName());

        timerText = (TextView) findViewById(R.id.timer);

        final Button openCloseGearbox = (Button) findViewById(R.id.openCloseGearbox);
        openCloseGearbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open = !open;
                progressDialog = new ProgressDialog(CurrentlyPlaying.this);
                progressDialog.setMessage(open ? "Opening gearbox..." : "Locking gearbox...");
                progressDialog.show();
                openCloseGearbox.setText(open ? "Close gearbox" : "Open gearbox");
                Ion.with(CurrentlyPlaying.this, getString(R.string.server) + (open ? getString(R.string.open_endpoint) : getString(R.string.close_endpoint)))
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                Intent intent = new Intent(CurrentlyPlaying.this, ThankYouActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                            }
                        });
            }
        });

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
            timerText.setText(String.format("2:04:%02d", time));
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
                        .setSmallIcon(R.drawable.gearboxnotification)
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lock box");
        builder.setMessage("Please place the gear in the box.");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog = new ProgressDialog(CurrentlyPlaying.this);
                progressDialog.setMessage("Locking box");
                progressDialog.show();
                Ion.with(CurrentlyPlaying.this, getString(R.string.server) + getString(R.string.close_endpoint))
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                Log.d(TAG, "Latch closed, result: " + result);
                                Intent intent = new Intent(CurrentlyPlaying.this, ThankYouActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                            }
                        });
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();

    }
}
