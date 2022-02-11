/*  FILE            : MainActivity.java
*   PROJECT         : PROG3150
*   PROGRAMMER      : Justin Funk & Yutong Ji & Tong Mu & Zijia Cao
*   FIRST VERSION   : 2022/02/09
*   DESCRIPTION     : This file is the main screen of this Android
*                     application, it has a lot of buttons to go to other Activity.
*/

package com.example.mad_a1_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String dest="N/a";
    String hotel="N/a";
    int ticketPrice=0;
    int hotelPrice=0;
    int hotelNigths=0;
    int sightPrice=0;

    Bundle bundle=new Bundle();
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recive the data that come from other activity
        Intent intent = getIntent();
        if(intent.hasExtra("bundle")) {
            bundle = intent.getBundleExtra("bundle");
            dest=bundle.getString("dest");
            hotel=bundle.getString("hotel");
            ticketPrice=bundle.getInt("ticketPrice");
            hotelPrice=bundle.getInt("hotelPrice");
            sightPrice=bundle.getInt("sightPrice");
            hotelNigths=bundle.getInt("hotelNigths");
            arrayList=bundle.getStringArrayList("list");
        }

        if(!dest.equals("na")){
            if(dest.startsWith("Tor")) dest="Toronto";
            else if(dest.startsWith("Van")) dest="Vancouver";
        }

        ListView lvM=null;
        ArrayAdapter<String> adapter=null;
        TextView textView7=findViewById(R.id.textView7);

        if(arrayList!=null){
            textView7.setVisibility(TextView.VISIBLE);
            lvM = findViewById(R.id.lvM);
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,arrayList);
            lvM.setAdapter(adapter);
        }

        TextView tvCity=findViewById(R.id.tvCity);
        TextView tvTicketPrice=findViewById(R.id.tvTicketPrice);
        TextView tvHotle=findViewById(R.id.tvHotle);
        TextView tvHotlePrice=findViewById(R.id.tvHotlePrice);
        TextView tvHotleNights=findViewById(R.id.tvHotleNights);
        TextView tvSightPrice=findViewById(R.id.tvSightPrice);

        SeekBar sbNights=(SeekBar)findViewById(R.id.nightsSeekBar);

        sbNights.setProgress(hotelNigths);
        tvHotleNights.setText(String.valueOf(hotelNigths));
        tvHotlePrice.setText(String.valueOf(hotelNigths*hotelPrice));

        sbNights.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                hotelNigths=i;
                tvHotleNights.setText(String.valueOf(hotelNigths));
                tvHotlePrice.setText(String.valueOf(hotelNigths*hotelPrice));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tvTicketPrice.setText(String.valueOf(ticketPrice));
        tvCity.setText(dest);
        tvHotle.setText(hotel);
        tvSightPrice.setText(String.valueOf(sightPrice));

        //buttons
        Button btnAdd=findViewById(R.id.btnAdd);
        Button btnHotel=findViewById(R.id.btnHotel);
        Button btnSight=findViewById(R.id.btnSight);
        Button btnSummary=findViewById(R.id.btnSummary);

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
                extras.putInt("ticketPrice",ticketPrice);
                intent.putExtra("bundle",extras);
                startActivity(intent);
            }
        });

        btnSight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,selectPage.class);
                String Purpose="sight";
                Bundle extras=new Bundle();
                extras.putString("Purpose",Purpose);
                extras.putString("dest",dest);
                extras.putString("hotel",hotel);
                extras.putInt("hotelNigths",hotelNigths);
                extras.putInt("hotelPrice",hotelPrice);
                extras.putInt("ticketPrice",ticketPrice);
                extras.putInt("hotelNigths",hotelNigths);
                intent.putExtra("bundle",extras);
                startActivity(intent);
            }
        });

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayList==null){
                    Toast.makeText(MainActivity.this,"You have many things not select.",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, summary.class);
                    Bundle extras = new Bundle();
                    extras.putString("dest", dest);
                    extras.putString("hotel", hotel);
                    extras.putInt("hotelNigths", hotelNigths);
                    extras.putInt("hotelPrice", hotelPrice);
                    extras.putInt("ticketPrice", ticketPrice);
                    extras.putInt("hotelNigths", hotelNigths);
                    intent.putExtra("bundle", extras);
                    startActivity(intent);
                }
            }
        });
    }

}