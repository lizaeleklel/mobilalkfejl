package com.example.strike_scheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    private NotificationHandler mNotificationHandler;
    private SharedPreferences preferences;
    FirebaseUser user;
    Spinner duration;
    Spinner startHour;
    Spinner appointmentDateSpinner;
    EditText note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        user = FirebaseAuth.getInstance().getCurrentUser();

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if (secret_key != SECRET_KEY) {
            finish();
        }

        Log.e(LOG_TAG, "itt wok");

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);

        appointmentDateSpinner = findViewById(R.id.spinner_appointment_date);
        List<String> dateOptions = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        dateOptions.add(dateFormat.format(calendar.getTime())); // Add today's date
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DATE, 1);
            dateOptions.add(dateFormat.format(calendar.getTime())); // Add the next six days
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointmentDateSpinner.setAdapter(adapter);


        duration = findViewById(R.id.spinner_appointment_duration);
        startHour = findViewById(R.id.spinner_appointment_start_hour);
        note = findViewById(R.id.note);

        duration.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.createFromResource(this,
                R.array.appointment_duration, android.R.layout.simple_spinner_item);
        durationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        duration.setAdapter(durationAdapter);

        startHour.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> startHourAdapter = ArrayAdapter.createFromResource(this,
                R.array.appointment_start_hour, android.R.layout.simple_spinner_item);
        durationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startHour.setAdapter(startHourAdapter);


        mNotificationHandler = new NotificationHandler(this);

        Log.i(LOG_TAG, "onCreate");
    }

    public void booking(View view) {
        //Toast.makeText(this, "You successfully booked your appointment!", Toast.LENGTH_SHORT).show();
        mNotificationHandler.send("You successfully booked your appointment!");
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}