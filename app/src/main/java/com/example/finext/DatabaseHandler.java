package com.example.finext;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.finext.model.incomeModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private DatabaseReference databaseReference;

    public DatabaseHandler() {
        // Get a reference to the root node of the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public boolean addData(String amount, String type, String note, String date) {
        String key = databaseReference.child("income_data").push().getKey();
        incomeModel income = new incomeModel(key, amount, type, note, date);
        databaseReference.child("income_data").child(key).setValue(income);

        // Return true if the data was added successfully
        return true;
    }

    public void update(String id, String amount, String type, String note, String date) {
        incomeModel income = new incomeModel(id, amount, type, note, date);
        databaseReference.child("income_data").child(id).setValue(income);
    }

    public void getAllIncome(final DataCallback<List<incomeModel>> callback) {
        databaseReference.child("income_data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String previousChildKey) {
                incomeModel income = dataSnapshot.getValue(incomeModel.class);
                callback.onCallback(income);
            }

            // Implement the remaining ChildEventListener methods if needed

            // ...

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle onCancelled event if needed
            }
        });
    }

    // Define a callback interface to handle asynchronous data retrieval
    public interface DataCallback<T> {
        void onCallback(T data);
    }
}

