package com.example.mad_a1_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String dest="na";
    String hotel="na";
    int ticketPrice=0;
    int hotelPrice=0;
    int hotelNigths=0;

    Bundle bundle=new Bundle();

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
        }

        if(!dest.equals("na")){
            if(dest.startsWith("Tor")) dest="Toronto";
            else if(dest.startsWith("Van")) dest="Vancouver";
        }

        TextView tvCity=findViewById(R.id.tvCity);
        TextView tvTicketPrice=findViewById(R.id.tvTicketPrice);
        TextView tvHotle=findViewById(R.id.tvHotle);
        TextView tvHotlePrice=findViewById(R.id.tvHotlePrice);
        TextView tvHotleNights=findViewById(R.id.tvHotleNights);
        SeekBar sbNights=(SeekBar)findViewById(R.id.nightsSeekBar);

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
                extras.putInt("ticketPrice",ticketPrice);
                intent.putExtra("bundle",extras);
                startActivity(intent);
            }
        });
    }

}