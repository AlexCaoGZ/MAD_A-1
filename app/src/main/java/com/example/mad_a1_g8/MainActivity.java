/*  FILE            : MainActivity.java
*   PROJECT         : PROG3150
*   PROGRAMMER      : Justin Fink & Yutong Ji & Tong Mu & Zijia Cao
*   FIRST VERSION   : 2022/02/09
*   DESCRIPTION     : This file is the main screen of this Android
*                     application, it has a lot of buttons to go to other Activity.
*/

package com.example.mad_a1_g8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String dest="N/a";
    String hotel="N/a";
    int ticketPrice=0;
    int hotelPrice=0;
    int hotelNigths=0;
    int sightPrice=0;
    int vehicle=0;
    int city=0;

    Bundle bundle=new Bundle();
    ArrayList<String> arrayList = new ArrayList<>();

    TextView tvTicketPrice=null;

    StringBuilder sb = null;

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

        //Since the return String also contain the km and ticket price, we need to remove those stuff.
        if(!dest.equals("na")){
            if(dest.startsWith("Tor")) dest="Toronto";
            else if(dest.startsWith("Van")) dest="Vancouver";
        }

        //set up our ListView for showing sights that user selected
        ListView lvM=null;
        ArrayAdapter<String> adapter=null;
        TextView textView7=findViewById(R.id.textView7);

        //if user not select any sight yet, jump over this part.
        if(arrayList!=null){
            textView7.setVisibility(TextView.VISIBLE);
            lvM = findViewById(R.id.lvM);
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,arrayList);
            lvM.setAdapter(adapter);
        }

        //get TextViews ready
        tvTicketPrice=findViewById(R.id.tvTicketPrice);
        TextView tvHotle=findViewById(R.id.tvHotle);
        TextView tvHotlePrice=findViewById(R.id.tvHotlePrice);
        TextView tvHotleNights=findViewById(R.id.tvHotleNights);
        TextView tvSightPrice=findViewById(R.id.tvSightPrice);

        //get spinner ready
        Spinner citySpinner = findViewById(R.id.citySpinner);
        String[] citySpinnerList={"Toronto    114Km","Vancouver   4,171Km"};
        ArrayAdapter<String> citySpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,citySpinnerList);
        citySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(citySpinnerAdapter);

        if(dest=="Toronto"){
            citySpinner.setSelection(0);
        }else{
            citySpinner.setSelection(1);
        }

        //select event for spinner
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    city=Constant.VAN;
                    dest="Vancouver";
                }else{
                    city=Constant.TOR;
                    dest="Toronto";
                }
                ticketPriceCalculator();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RadioGroup rg_vehicle = findViewById(R.id.rG_vehicle);
        RadioButton rb_flight = findViewById(R.id.rB_flight);
        RadioButton rb_bus = findViewById(R.id.rB_bus);
        RadioButton rb_train = findViewById(R.id.rB_train);

        if(vehicle==Constant.TRAIN){
            rb_train.setChecked(true);
        }else if(vehicle==Constant.BUS){
            rb_bus.setChecked(true);
        }else if(vehicle==Constant.FLIGHT){
            rb_flight.setChecked(true);
        }

        rg_vehicle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rB_bus:
                        vehicle=Constant.BUS;
                        break;
                    case R.id.rB_flight:
                        vehicle=Constant.FLIGHT;
                        break;
                    case R.id.rB_train:
                        vehicle = Constant.TRAIN;
                        break;
                }
                ticketPriceCalculator();
            }
        });

        //SeekBar for selecting how many nights
        SeekBar sbNights=(SeekBar)findViewById(R.id.nightsSeekBar);

        //for calculate the hotel's price
        sbNights.setProgress(hotelNigths);
        tvHotleNights.setText(String.valueOf(hotelNigths));
        tvHotlePrice.setText(String.valueOf(hotelNigths*hotelPrice));

        //When the SeekBar move, the price that displayed on the screen will be updated
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

        //set the destination and hotle's name
        tvTicketPrice.setText(String.valueOf(ticketPrice));
        //tvCity.setText(dest);
        tvHotle.setText(hotel);
        tvSightPrice.setText(String.valueOf(sightPrice));

        //buttons
        //Button btnAdd=findViewById(R.id.btnAdd);
        Button btnHotel=findViewById(R.id.btnHotel);
        Button btnFindHotel=findViewById(R.id.btnFindHotel);
        Button btnSight=findViewById(R.id.btnSight);
        Button btnSummary=findViewById(R.id.btnSummary);

        //the button that go to selectPage.java
        //it will carry Purpose will it.

        //the button that go to selectPage.java, but in hotel selecting mode
        //Since start a new activity will kill the old one, we need to carry those vars from the old one with us.
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

        //Used to open a hotel finding website and then the
        btnFindHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);
                //String link = "https://www.trivago.ca/?aDateRange%5Barr%5D=2022-03-27&aDateRange%5Bdep%5D=2022-03-28&aPriceRange%5Bfrom%5D=0&aPriceRange%5Bto%5D=0&iRoomType=7&aRooms%5B0%5D%5Badults%5D=2&cpt2=24999%2F200&hasList=1&hasMap=1&bIsSeoPage=0&sortingId=1&slideoutsPageItemId=&iGeoDistanceLimit=20000&address=&addressGeoCode=&offset=0&ra=&overlayMode=";

                //Intent openUrl = new Intent(Intent.ACTION_VIEW);
                //openUrl.setData(Uri.parse(link));
                //startActivity(openUrl);
            }
        });

        //Same stuff, but sight selecting mode this time.
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

        //basely is same stuff, goto summary.java
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dest.equals("N/a")){
                    //if the dest is not selected, we cant get into summary
                    //you doesnt need hotel if you know someone in that city
                    //you doesnt need to visit any sight
                    //but you always need a ticket
                    //unless you want to walk to there.
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu,menu);
        sb = new StringBuilder();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuDatas){
            Intent intent = new Intent(MainActivity.this, Database.class);
            Bundle extras = new Bundle();
            extras.putString("dest", dest);
            extras.putString("hotel", hotel);
            extras.putInt("hotelNigths", hotelNigths);
            extras.putInt("hotelPrice", hotelPrice);
            extras.putInt("sightPrice",sightPrice);
            extras.putInt("hotelNigths", hotelNigths);
            intent.putExtra("bundle", extras);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void ticketPriceCalculator(){
        if(city!=0&&vehicle!=0){
            if(city==Constant.TOR){
                switch (vehicle){
                    case Constant.BUS:
                        tvTicketPrice.setText("11");
                        ticketPrice=11;
                        break;
                    case Constant.FLIGHT:
                        tvTicketPrice.setText("200");
                        ticketPrice=200;
                        break;
                    case Constant.TRAIN:
                        tvTicketPrice.setText("20");
                        ticketPrice=20;
                        break;
                }
            }else if(city==Constant.VAN){
                switch (vehicle){
                    case Constant.BUS:
                        tvTicketPrice.setText("300");
                        ticketPrice=300;
                        break;
                    case Constant.FLIGHT:
                        tvTicketPrice.setText("500");
                        ticketPrice=500;
                        break;
                    case Constant.TRAIN:
                        tvTicketPrice.setText("400");
                        ticketPrice=400;
                        break;
                }
            }
        }
    }

}