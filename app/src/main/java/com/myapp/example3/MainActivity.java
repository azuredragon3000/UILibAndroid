package com.myapp.example3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.myapp.mylibrary.MyLib;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLib hello = new MyLib("a","b");
    }
}