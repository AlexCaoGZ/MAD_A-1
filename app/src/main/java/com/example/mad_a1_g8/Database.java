package com.example.mad_a1_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Database extends AppCompatActivity {

    String dest="N/a";
    String hotel="N/a";
    int hotelPrice=0;
    int hotelNigths=0;
    int sightPrice=0;
    int vehicle=0;

    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //recive the data that come from other activity
        Intent intent = getIntent();
        if(intent.hasExtra("bundle")) {
            bundle = intent.getBundleExtra("bundle");
            dest=bundle.getString("dest");
            hotel=bundle.getString("hotel");
            hotelPrice=bundle.getInt("hotelPrice");
            sightPrice=bundle.getInt("sightPrice");
            hotelNigths=bundle.getInt("hotelNigths");
            vehicle=bundle.getInt("vehicle");
        }
        
        //load from database


    }
}