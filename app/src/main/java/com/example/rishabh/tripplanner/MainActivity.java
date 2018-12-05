package com.example.rishabh.tripplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
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
                } else {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET,
                            "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Urbana,%20IL&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyAwRH6Junl7BV235LakrC3jzV7fCQeEtDg",
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(final JSONObject response) {
                                    //Log.d(TAG, response.toString());
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.w(TAG, error.toString());
                        }
                    });
                    hotel.setText(jsonObjectRequest.toString());
                    weather.setText("");
                    restaurant.setText("");
                }
            }
        });
    }
}