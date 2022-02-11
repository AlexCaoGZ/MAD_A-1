package com.example.mad_a1_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class selectPage extends AppCompatActivity {
    private ListView lv;
    String[] cities={"Toronto    114Km  $11","Vancouver   4,171Km   $417"};
    int[] ticketPrice={11,417};

    String[] hotelTOR={"TORhotelA","TORhotelB"};
    String[] hotelTORwithPrice={"TORhotelA   $20/Night","TORhotelB  $30/Night"};
    int[] hotelTORPrice={20,30};

    String[] hotelVANwithPrice={"VANhotelA   $15/Night","VANhotelB  $25/Night"};
    int[] hotelVANPrice={15,25};
    String[] hotelVAN={"VANhotelA","VANhotelB"};

    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);
        String pagePurpose = "";
        String dest = "";

        Intent intent = getIntent();
        if (intent.hasExtra("bundle")) {
            bundle = intent.getBundleExtra("bundle");
            dest = bundle.getString("dest");
            pagePurpose=bundle.getString("Purpose");
        }

        lv = (ListView) findViewById(R.id.lv);

        if (pagePurpose.equals("dest")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectPage.this, android.R.layout.simple_list_item_single_choice, cities);
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            lv.setAdapter(adapter);

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
        } else if (pagePurpose.equals("hotel")) {
            if (dest.equals("na")) {
                //dest did not selected, cant show the hotels
            } else if (dest.equals("Toronto")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectPage.this, android.R.layout.simple_list_item_single_choice, hotelTORwithPrice);
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                lv.setAdapter(adapter);

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
            } else if (dest.equals("Vancouver")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(selectPage.this, android.R.layout.simple_list_item_single_choice, hotelVAN);
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                lv.setAdapter(adapter);

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
        else if(pagePurpose.equals("sight")){

        }
    }
}