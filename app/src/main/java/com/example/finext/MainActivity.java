package com.example.finext;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finext.fragments.Dashboard;
import com.example.finext.fragments.Expense;
import  com.example.finext.fragments.Income;

import com.google.firebase.FirebaseApp;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

         bottomNavigatiionView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.SetSelectedItemId(R.id.dashboard);


    }

    Dashboard dashboardFragment = new Dashboard();
    Expense expenseFragment = new Expense();
    Income incomeFragment = new Income();

    @Override
    public boolean onNavigationItemReselected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.income) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, incomeFragment).commit();
            return true;
        }
        return false;
    }

}
