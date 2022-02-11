package com.example.mad_a1_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String dest="na";
    String hotel="na";

    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //resume datas
        if(savedInstanceState != null)
        {
            dest = (String)savedInstanceState.getString("dest");
            hotel = (String)savedInstanceState.getString("hotel");
        }

        //recive the data that come from other activity
        Intent intent = getIntent();
        if(intent.hasExtra("bundle")) {
            bundle = intent.getBundleExtra("bundle");
            dest=bundle.getString("dest");
            hotel=bundle.getString("hotel");
        }

        if(!dest.equals("na")){
            if(dest.startsWith("Tor")) dest="Toronto";
            else if(dest.startsWith("Van")) dest="Vancouver";
        }

        TextView tvCity=findViewById(R.id.tvCity);
        TextView tvHotle=findViewById(R.id.tvHotle);
        tvCity.setText(dest);
        tvHotle.setText(hotel);

        //buttons
        Button btnAdd=findViewById(R.id.btnAdd);
        Button btnHotel=findViewById(R.id.btnHotel);

        //the button that go to add.java
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,selectPage.class);
                String Purpose="dest";
                bundle.putString("Purpose",Purpose);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

        //the button that go to hotel.java
        btnHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,selectPage.class);
                String Purpose="hotel";
                Bundle extras=new Bundle();
                extras.putString("Purpose",Purpose);
                extras.putString("dest",dest);
                intent.putExtra("bundle",extras);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("dest", dest);
        outState.putString("hotel", hotel);
    }
}