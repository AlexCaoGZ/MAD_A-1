/*  FILE            : selectPage.java
 *  PROJECT         : PROG3150
 *  PROGRAMMER      : Justin Funk & Yutong Ji & Tong Mu & Zijia Cao
 *  FIRST VERSION   : 2022/02/09
 *  DESCRIPTION     : This file is the selection screen of this Android application, it can
 *                    show 3 different contents depending on the information contained in the intent.
 */
package com.example.mad_a1_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class selectPage extends AppCompatActivity {
    //we hardcode a lot things here
    private ListView lv;
    String[] cities={"Toronto    114Km  $11","Vancouver   4,171Km   $417"};
    int[] ticketPrice={11,417};

    String[] hotelTOR={"TORhotelA","TORhotelB"};
    String[] hotelTORwithPrice={"TORhotelA   $20/Night","TORhotelB  $30/Night"};
    int[] hotelTORPrice={20,30};
    String[] sightTOR={"TORsightA","TORsightB"};
    String[] sightTORwithPrice={"TORsightA  $50","TORsightB $80"};
    int[] sightTORPrice={50,80};

    String[] hotelVANwithPrice={"VANhotelA   $15/Night","VANhotelB  $25/Night"};
    int[] hotelVANPrice={15,25};
    String[] hotelVAN={"VANhotelA","VANhotelB"};
    String[] sightVAN={"TANsightA","VANsightB"};
    String[] sightVANwithPrice={"VANsightA  $60","VANsightB $70"};
    int[] sightVANPrice={60,70};

    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);
        String pagePurpose = "";
        String dest = "";

        //get the intent, this activity only care about dest and pagePurpose
        Intent intent = getIntent();
        if (intent.hasExtra("bundle")) {
            bundle = intent.getBundleExtra("bundle");
            dest = bundle.getString("dest");
            pagePurpose=bundle.getString("Purpose");
        }

        //peparae our ListView
        lv = (ListView) findViewById(R.id.lv);

        //This is the only button in this activity, it will only show up when adding sight
        Button btnAddSight=findViewById(R.id.btnAddSight);

        //show different content based on "pagePurpose"
        if (pagePurpose.equals("dest")) {   //selecting destination

            //load the String[]cities into adapter, then load into ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectPage.this, android.R.layout.simple_list_item_single_choice, cities);
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            lv.setAdapter(adapter);

            //this Listener will return the destination to MainActivity
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(selectPage.this, MainActivity.class);
                    bundle.putString("dest",cities[i]);
                    bundle.putInt("ticketPrice",ticketPrice[i]);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            });
        } else if (pagePurpose.equals("hotel")) {   //selecting hotel

            if (dest.equals("N/a")) {
                //dest did not selected, cant show the hotels
                Toast.makeText(selectPage.this,"City didnt selected.",Toast.LENGTH_LONG).show();
            }
            else if (dest.equals("Toronto")) {
                //this time load the String[]hotel
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectPage.this, android.R.layout.simple_list_item_single_choice, hotelTORwithPrice);
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                lv.setAdapter(adapter);

                //return the selected hotel
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(selectPage.this, MainActivity.class);
                        bundle.putString("hotel",hotelTOR[i]);
                        bundle.putInt("hotelPrice",hotelTORPrice[i]);
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    }
                });
            }
            else if (dest.equals("Vancouver")) {
                //Same stuff
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectPage.this, android.R.layout.simple_list_item_single_choice, hotelVANwithPrice);
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                lv.setAdapter(adapter);
                //Same stuff too
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(selectPage.this, MainActivity.class);
                        bundle.putString("hotel",hotelVAN[i]);
                        bundle.putInt("hotelPrice",hotelVANPrice[i]);
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    }
                });
            }
        }
        else if(pagePurpose.equals("sight")){   //selecting sight
            //make our one and only button visible.
            btnAddSight.setVisibility(Button.VISIBLE);

            if(dest.equals("Toronto")){
                //Same stuff
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectPage.this, android.R.layout.simple_list_item_single_choice, sightTORwithPrice);
                lv.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
                //Creat a ArrayList for our selected sight, people may select many sight.
                //although we only have two for them.
                ArrayList<String> arrayList = new ArrayList<>();

                //this Listener will add/remove the sight  from ArrayList
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(arrayList.contains(sightTOR[i])){
                            arrayList.remove(sightTOR[i]);
                        }
                        else{
                            arrayList.add(sightTOR[i]);
                        }
                    }
                });

                //this Listener will calculate the ticket's price, and return to MainActivity
                btnAddSight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(selectPage.this, MainActivity.class);
                        int sightPrice=0;
                        //Since we only have 2 opinions, so I decide to do this in a stupid way
                        if(arrayList.contains(sightTOR[0])) sightPrice=sightPrice+sightTORPrice[0];
                        if(arrayList.contains(sightTOR[1])) sightPrice=sightPrice+sightTORPrice[1];

                        bundle.putStringArrayList("list",arrayList);
                        bundle.putInt("sightPrice",sightPrice);

                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    }
                });
            }
            else if(dest.equals("Vancouver")){ //Same stuff but for different city
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectPage.this, android.R.layout.simple_list_item_single_choice, sightVANwithPrice);
                lv.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
                ArrayList<String> arrayList = new ArrayList<>();

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(arrayList.contains(sightVAN[i])){
                            arrayList.remove(sightVAN[i]);
                        }
                        else{
                            arrayList.add(sightVAN[i]);
                        }
                    }
                });
                //Still same stuff
                btnAddSight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(selectPage.this, MainActivity.class);
                        int sightPrice=0;
                        //Since we only have 2 datas, so I decide to do this in a stupid way
                        if(arrayList.contains(sightVAN[0])) sightPrice=sightPrice+sightVANPrice[0];
                        if(arrayList.contains(sightVAN[1])) sightPrice=sightPrice+sightVANPrice[1];

                        bundle.putStringArrayList("list",arrayList);
                        bundle.putInt("sightPrice",sightPrice);

                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}