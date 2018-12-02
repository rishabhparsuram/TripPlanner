package com.example.rishabh.tripplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText city;
    private Button touchme;
    private TextView hotel;
    private TextView restaurant;
    private TextView weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (EditText) findViewById(R.id.cityName);
        hotel = (TextView) findViewById(R.id.hotels);
        weather = (TextView) findViewById(R.id.weather);
        touchme = (Button) findViewById(R.id.enter);
        restaurant = (TextView) findViewById(R.id.FoodPlaces);
        touchme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (city.getText().toString().length() == 0) {
                    hotel.setText("Please enter a Valid City");
                    restaurant.setText("Please enter a Valid City");
                    weather.setText("Please enter a Valid City");
                }
            }
        });
    }
}