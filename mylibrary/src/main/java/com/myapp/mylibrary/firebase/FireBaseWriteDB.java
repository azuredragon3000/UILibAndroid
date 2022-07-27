package com.myapp.mylibrary.firebase;

import com.google.firebase.database.DatabaseReference;

public class FireBaseWriteDB {

    public FireBaseWriteDB(DatabaseReference mDatabase){
        mDatabase.child("cuong").setValue("abc");
    }
}
