package com.example.strike_scheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Handler;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class BowlingActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bowling);

        user = FirebaseAuth.getInstance().getCurrentUser();

        GifImageView gifImageView = findViewById(R.id.gif_image_view);
        gifImageView.setImageResource(R.drawable.loop);

        TextView prices_TextView = findViewById(R.id.prices_textview);
        prices_TextView.setText(Html.fromHtml("<b>PRICES</b><br><br>" +
                "Every day<br>" +
                "From opening - 4 pm: $22/hour<br>" +
                "4 pm - Closing time: $30/hour<br><br>" +
                "Socks: 2$/pair<br>" +
                "<i>(Socks can be bought on the spot, for hygiene reasons shoes can only be used with socks!)</i><br>" +
                "The prices include a fee for the provision of shoes."));

        TextView track_booking_TextView = findViewById(R.id.track_booking_textview);
        track_booking_TextView.setText(Html.fromHtml("<b>TRACK BOOKING</b><br><br>" +
                "A reservation that has been approved but not started will be cancelled 15 minutes after the start of the reservation without any indication from the guest.<br>" +
                "Track reservation here (name and email are obligatory), for reservations by phone only name and phone number are obligatory!<br>" +
                "The maximum number of players on a course is <b>6</b>!"));

        TextView foodlist = findViewById(R.id.food);
        foodlist.setText(Html.fromHtml("<b>FOOD</b>" +
                "<br><br>" +
                "Pizza<br>" +
                "Burger<br>" +
                "Veggie Burger<br>" +
                "Grilled Cheese Sandwich<br>" +
                "Celery and carrot with hummus<br>" +
                "Nachos plate with 2 selected dips<br>" +
                "French fries with a selected dip<br>" +
                "<br>" +
                "<i>About our pizza and burger variety, ask the staff!</i>"));

        TextView drinklist = findViewById(R.id.drinks);
        drinklist.setText(Html.fromHtml("<b>DRINKS</b>" +
                "<br><br>" +
                "Mineral water<br>" +
                "Coca-Cola, Fanta, Tonic<br>" +
                "Orange, Apple, Peach, Berry Juice<br>" +
                "<br>" +
                "RedBull, Monster<br>" +
                "Latte, Capuccino, Espresso<br>" +
                "<br>" +
                "<i>For alchoholic beverages, ask the staff for our list!</i>"));


        final Handler handler = new Handler();
        ImageButton imageButton_button_bowling = findViewById(R.id.button_bowling);
        imageButton_button_bowling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                imageButton_button_bowling.startAnimation(animRotate);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bowling(view);
                    }
                }, 800);
            }
        });

        ImageButton imageButton_button_profile = findViewById(R.id.button_profile);
        imageButton_button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                imageButton_button_profile.startAnimation(animRotate);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        profile(view);
                    }
                }, 800);
            }
        });

        ImageButton imageButton_button_logout = findViewById(R.id.button_logout);
        imageButton_button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                imageButton_button_logout.startAnimation(animRotate);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        logout(view);
                    }
                }, 800);

            }
        });

        Button booking_button = findViewById(R.id.booking_button);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Handler fadeHandler = new Handler();
        fadeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                booking_button.startAnimation(fadeInAnimation);
            }
        }, 1500);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if (secret_key != SECRET_KEY){
            finish();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            finish();
        }
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }

    public void logout(View view) {
        Log.d(LOG_TAG, "Log out clicked!");
        Toast.makeText(BowlingActivity.this, "You logged out", Toast.LENGTH_SHORT).show();
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void bowling(View view) {
        Intent intent = new Intent(this, BowlingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void goToBooking(View view) {
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}