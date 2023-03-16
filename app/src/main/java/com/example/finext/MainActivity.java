package com.example.finext;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        //onclick listener
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton btn = findViewById(R.id.accButton);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LogoutActivity.class);
            startActivity(intent);
        });

    }
}
