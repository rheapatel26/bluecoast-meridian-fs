package com.example.bluecoastmeridianfsproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button lbotton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        lbotton = findViewById(R.id.loginButton); // Ensure this is not commented

        lbotton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Basic validation
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show();
            } else {
                // Handle login logic (e.g., check credentials)
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                // Proceed to the next screen or activity
            }
        });
    }
}
