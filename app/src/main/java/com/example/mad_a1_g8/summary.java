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

public class summary extends AppCompatActivity {
    String dest="na";
    String hotel="na";
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

        tvSum.setText("$"+String.valueOf(sum));

        EditText etName = findViewById(R.id.etName);
        EditText etPhone = findViewById(R.id.etPhone);

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                people++;
                tvPeople.setText(String.valueOf(people));
                sum=sumForOne*people;
                tvSum.setText("$"+String.valueOf(sum));
            }
        });

        ivMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(people==0){

                }
                else{
                    people--;
                }
                sum=sumForOne*people;
                tvSum.setText("$"+String.valueOf(sum));
                tvPeople.setText(String.valueOf(people));
            }
        });

        tvSumCity.setText(dest);
        tvSumHotel.setText(hotel);
        tvSumHotelNights.setText(String.valueOf(hotelNigths));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(summary.this,"Thank you for your cooperation.",Toast.LENGTH_LONG).show();
            }
        });
    }
}