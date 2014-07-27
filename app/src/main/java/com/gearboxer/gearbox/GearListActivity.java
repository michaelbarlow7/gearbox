package com.gearboxer.gearbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gearboxer.gearbox.adapter.ComponentListAdapter;
import com.gearboxer.gearbox.adapter.GearListAdapter;
import com.gearboxer.gearbox.model.Gear;
import com.gearboxer.gearbox.model.GearLocation;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class GearListActivity extends Activity {
    private static final String TAG = GearListActivity.class.getName();
    public static String LOCATION_EXTRA = "LOCATION_EXTRA";
    private GearLocation gearLocation;
    private static final String PAYPAL_CLIENT_ID = "AYa-7hCXYEbJ-i3iz6wcnviNJkg0ni42YDVj5ocrV41aAh3ufYX_BlMor0TG";
    private static PayPalConfiguration config = new PayPalConfiguration()

            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
//            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)

            .clientId(PAYPAL_CLIENT_ID);
    private Gear gear;

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
                gear = (Gear) parent.getItemAtPosition(position);

                // Launch dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle(gear.getGearType().getName());
                View dialogView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_gear, null);

                ListView componentListView = (ListView) dialogView.findViewById(R.id.componentListView);
                ComponentListAdapter adapter = new ComponentListAdapter(gear.getGearType().getComponents());
                componentListView.setAdapter(adapter);

                builder.setView(dialogView);

                // On click goes here
                builder.setPositiveButton("Grab gear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // PAYMENT_INTENT_SALE will cause the payment to complete immediately.
                        // Change PAYMENT_INTENT_SALE to PAYMENT_INTENT_AUTHORIZE to only authorize payment and
                        // capture funds later.

                        PayPalPayment payment = new PayPalPayment(new BigDecimal("2.00"), "AUD", gear.getGearType().getName(),
                                PayPalPayment.PAYMENT_INTENT_SALE);

                        Intent intent = new Intent(GearListActivity.this, PaymentActivity.class);

                        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

                        startActivityForResult(intent, 0);
                    }
                });

                builder.show();
            }
        });

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
//        Ion.with(this, getString(R.string.server) + getString(R.string.open_endpoint))
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        Log.d(TAG, "Latch opened, result: " + result);
//                    }
//                });
        Intent intent = new Intent(this, CurrentlyPlaying.class);
        intent.putExtra(CurrentlyPlaying.LOCATION_EXTRA, gearLocation);
        intent.putExtra(CurrentlyPlaying.GEAR_EXTRA, gear);
        startActivity(intent);
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
//            confirm.toJSONObject()
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
    }
}
