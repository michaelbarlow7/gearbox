package com.gearboxer.gearbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ThankYouActivity extends Activity {
    private static final String TAG = ThankYouActivity.class.getName();
    private ImageView splashBall0;
    private ImageView splashBall1;
    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;
    private int fadeInBall = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        ImageView splashGear = (ImageView) findViewById(R.id.splashGear);
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        splashGear.startAnimation(rotateAnimation);

        splashBall0 = (ImageView) findViewById(R.id.ball0);
        splashBall1 = (ImageView) findViewById(R.id.ball1);

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        fadeInAnimation.setAnimationListener(new MyAnimationListener());

        splashBall0.startAnimation(fadeInAnimation);
        splashBall1.startAnimation(fadeOutAnimation);

    }

    public static int getBallResource(int position) {
        switch (position) {
            case 0:
                return R.drawable.splashvolley;
            case 1:
                return R.drawable.splashtennis;
            case 2:
                return R.drawable.splashsoccer;
            case 3:
                return R.drawable.splashbasket;
            case 4:
                return R.drawable.splashbeach;
            case 5:
                return R.drawable.splashcricket;
            default:
                return R.drawable.splashcricket;
        }
    }

    public void onContinueClick(View view) {
//        Intent intent = new Intent(this, MapActivity.class);
//        startActivity(intent);
//        finish();
//        Ion.with(this, getString(R.string.server) + getString(R.string.open_endpoint))
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        Log.d(TAG, "Latch opened, result: " + result);
//                    }
//                });
    }

    private class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            fadeInBall++;
            fadeInBall %= 6;
            if (fadeInBall % 2 == 0) {
                // ball 0 is due to fade in
                splashBall0.setImageResource(getBallResource(fadeInBall));
                fadeInAnimation = AnimationUtils.loadAnimation(ThankYouActivity.this, R.anim.fade_in);
                fadeInAnimation.setAnimationListener(new MyAnimationListener());
                splashBall0.startAnimation(fadeInAnimation);
                splashBall1.startAnimation(fadeOutAnimation);
            } else {
                // ball 1 is due to fade in
                splashBall1.setImageResource(getBallResource(fadeInBall));
                fadeInAnimation = AnimationUtils.loadAnimation(ThankYouActivity.this, R.anim.fade_in);
                fadeInAnimation.setAnimationListener(new MyAnimationListener());
                splashBall1.startAnimation(fadeInAnimation);
                splashBall0.startAnimation(fadeOutAnimation);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }


}
