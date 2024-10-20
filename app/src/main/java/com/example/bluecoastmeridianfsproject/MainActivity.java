package com.example.bluecoastmeridianfsproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView headname;
    private TextView headtag;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headname = findViewById(R.id.headname);
        headtag = findViewById(R.id.headtag);
        imageView = findViewById(R.id.imageView);

        // Load fade-in animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn.setDuration(1500); // Set fade-in duration to 1.5 seconds

        // Start fade-in for headname
        headname.setVisibility(View.VISIBLE); // Make headname visible
        headname.startAnimation(fadeIn);

        // Delay before headtag appears
        new Handler().postDelayed(() -> {
            headtag.setVisibility(View.VISIBLE); // Make headtag visible
            headtag.startAnimation(fadeIn);
        }, 1700); // Delay for headtag to appear after headname

        // Delay before imageView appears
        new Handler().postDelayed(() -> {
            imageView.setVisibility(View.VISIBLE); // Make imageView visible
            imageView.startAnimation(fadeIn);
        }, 3400); // Delay for imageView to appear after headtag

        // Delay to transition to the LoginActivity after all animations
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
            finish();
        }, 6000); // Total duration
    }
}
