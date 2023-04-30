package com.example.strike_scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BookingActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    private SharedPreferences preferences;
    FirebaseUser user;
    Spinner duration;
    Spinner start_hour;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        user = FirebaseAuth.getInstance().getCurrentUser();

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if (secret_key != SECRET_KEY){
            finish();
        }

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);



        Log.i(LOG_TAG, "onCreate");
    }

    public void booking(View view) {
        // TODO.
        Toast.makeText(this, "You succesfully booked your appointment at \n" , Toast.LENGTH_SHORT).show();
        //You succesfully booked your appointment at (10:00) for (4 hours)! See you there!
        finish();
    }

    public void cancel(View view) {
        finish();
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
}