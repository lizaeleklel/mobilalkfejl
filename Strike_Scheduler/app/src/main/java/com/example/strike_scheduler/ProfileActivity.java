package com.example.strike_scheduler;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    //Update and delete profile
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    EditText emailET;
    EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if (secret_key != SECRET_KEY){finish();}

        //write here
        emailET = findViewById(R.id.userEmailEditText);
        emailET.setText(user.getEmail());

        passwordET = findViewById(R.id.passwordEditText);

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


        Log.i(LOG_TAG, "onCreate");
    }

    public void save(View view) {
        String newEmail = emailET.getText().toString();
        String newPassword = passwordET.getText().toString();

        if (!newEmail.trim().equals("") && !newEmail.equals(user.getEmail())){
            if (user == null) return;
            user.updateEmail(newEmail).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Your email address updated successfully.\nPlease sign in again", Toast.LENGTH_LONG).show();
                    mAuth.signOut();
                    finish();
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to update your email address.\n" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        if (!newPassword.trim().equals("")){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) return;
            user.updatePassword(newPassword).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Password updated successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to update your password.\n" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void delete(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        user.delete().addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Toast.makeText(ProfileActivity.this, "Your account has been deleted!", Toast.LENGTH_SHORT).show();
                redirectToLogin();
            } else {
                Toast.makeText(ProfileActivity.this, "Failed to delete your account!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void redirectToLogin(){
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

    /* navbar  */
    public void logout(View view) {
        Log.d(LOG_TAG, "Log out clicked!");
        Toast.makeText(ProfileActivity.this, "You logged out", Toast.LENGTH_SHORT).show();
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

}