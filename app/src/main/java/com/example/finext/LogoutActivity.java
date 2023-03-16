package com.example.finext;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class LogoutActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button logoutBtn = findViewById(R.id.logout_button);
        mAuth = FirebaseAuth.getInstance();

        logoutBtn.setOnClickListener(view -> {
            mAuth.signOut();
            Toast.makeText(LogoutActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
