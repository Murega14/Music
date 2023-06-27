package com.example.finext;


import com.google.firebase.database.DatabaseReference;

public class DatabaseHandler {
    private final DatabaseReference databaseReference;

    public DatabaseHandler(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

// --Commented out by Inspection START (6/26/23, 8:24 PM):
//    public DatabaseHandler() {
//        // Get a reference to the root node of the Firebase Realtime Database
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//    }
// --Commented out by Inspection STOP (6/26/23, 8:24 PM)

// --Commented out by Inspection START (6/26/23, 8:24 PM):
//    public boolean addData(String amount, String type, String note, String date) {
//        String key = databaseReference.child("income_data").push().getKey();
//        incomeModel income = new incomeModel(key, amount, type, note, date);
//        databaseReference.child("income_data").child(key).setValue(income);
//
//        // Return true if the data was added successfully
//        return true;
//    }
// --Commented out by Inspection STOP (6/26/23, 8:24 PM)

// --Commented out by Inspection START (6/26/23, 8:24 PM):
//    public void update(String id, String amount, String type, String note, String date) {
//        incomeModel income = new incomeModel(id, amount, type, note, date);
//        databaseReference.child("income_data").child(id).setValue(income);
//    }
// --Commented out by Inspection STOP (6/26/23, 8:24 PM)

// --Commented out by Inspection START (6/26/23, 8:24 PM):
//    public void getAllIncome(final DataCallback<List<incomeModel>> callback) {
//        databaseReference.child("income_data").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String previousChildKey) {
//                incomeModel income = dataSnapshot.getValue(incomeModel.class);
//                callback.onCallback((List<incomeModel>) income);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            // Implement the remaining ChildEventListener methods if needed
//
//            // ...
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle onCancelled event if needed
//            }
//        });
//    }
// --Commented out by Inspection STOP (6/26/23, 8:24 PM)

    // Define a callback interface to handle asynchronous data retrieval
    public interface DataCallback<T> {
        void onCallback(T data);
    }
}

