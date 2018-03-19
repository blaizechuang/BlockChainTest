package com.dpt.ileopard.myblocktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.GsonBuilder;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Block> mChain = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initChain();
    }

    private void initChain() {
        new Chain().init();
    }
}


