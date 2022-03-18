/*  FILE            : selectPage.java
 *  PROJECT         : PROG3150
 *  PROGRAMMER      : Justin Fink & Yutong Ji & Tong Mu & Zijia Cao
 *  FIRST VERSION   : 2022/02/09
 *  DESCRIPTION     : This file is the selection screen of this Android application, it can
 *                    show 3 different contents depending on the information contained in the intent.
 */
package com.example.mad_a1_g8;

import static java.lang.System.exit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class submitData extends AppCompatActivity {
    String dest="N/a";
    String hotel="N/a";
    int people=1;
    int ticketPrice=0;
    int hotelPrice=0;
    int hotelNigths=0;
    int sightPrice=0;
    int sum=0;

    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //load the vars from intent
        Intent intent = getIntent();
        if(intent.hasExtra("bundle")) {
            bundle = intent.getBundleExtra("bundle");
            dest=bundle.getString("dest");
            hotel=bundle.getString("hotel");
            ticketPrice=bundle.getInt("ticketPrice");
            hotelPrice=bundle.getInt("hotelPrice");
            sightPrice=bundle.getInt("sightPrice");
            hotelNigths=bundle.getInt("hotelNigths");
        }

        //start calucate the price
        int sumForOne=ticketPrice+(hotelPrice*hotelNigths)+sightPrice;
        sum=sumForOne;

        Button btnSubmit = findViewById(R.id.btnSubmit);

        ImageView ivMin = findViewById(R.id.ivMin);
        ImageView ivAdd = findViewById(R.id.ivAdd);

        TextView tvPeople = findViewById(R.id.people);
        TextView tvSumCity = findViewById(R.id.tvSumCity);
        TextView tvSumHotel = findViewById(R.id.tvSumHotel);
        TextView tvSumHotelNights = findViewById(R.id.tvSumHotelNights);
        TextView tvSum = findViewById(R.id.tvSum);

        //set the price tag
        tvSum.setText("$"+String.valueOf(sum));

        //I declare them, but they are not being used.
        EditText etName = findViewById(R.id.etName);
        EditText etPhone = findViewById(R.id.etPhone);

        //the Listener for add one person to the "people" and update the price tag
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sum>=2147482479){
                    //just in case
                }
                else{
                    people++;
                    tvPeople.setText(String.valueOf(people));
                    sum=sumForOne*people;
                    tvSum.setText("$"+String.valueOf(sum));
                }
            }
        });

        //the Listener for minimize one person to the "people" and update the price tag
        ivMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(people==0){
                    //if you have -1 people travel with you, that will be very intersting.
                }
                else{
                    people--;
                }
                sum=sumForOne*people;
                tvSum.setText("$"+String.valueOf(sum));
                tvPeople.setText(String.valueOf(people));
            }
        });

        //setup the texts
        tvSumCity.setText(dest);
        //again, you can sleep under the bridge and not visit any sights
        //but you need a ticket to be there.
        //For Toronto sure you can walk there from Waterloo, or maybe I'll change Toronto to LA
        if(hotel==null){
            tvSumHotel.setText("N/a");
            tvSumHotelNights.setText("N/a");
        }
        else{
            tvSumHotel.setText(hotel);
            tvSumHotelNights.setText(String.valueOf(hotelNigths));
        }

        //Thank you for your patience all the way to read here.
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Need to make it so that when the user presses this button then all the
                // information is added to a database

                //Toast.makeText(summary.this,"Thank you for your cooperation.",Toast.LENGTH_LONG).show();

            }
        });
    }
}