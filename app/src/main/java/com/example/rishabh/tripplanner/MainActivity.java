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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
//import org.json.simple.*JSONArray;
//import org.json.simple.*JSONObject;
//import org.json.simple.parser.*;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static RequestQueue requestQueue;
    private EditText city;
    private Button touchme;
    private TextView hotel;
    private TextView restaurant;
    private TextView weather;
    private EditText state;
    private double latitude;
    private double longitude;
    private String api_key = "AIzaSyAwRH6Junl7BV235LakrC3jzV7fCQeEtDg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (EditText) findViewById(R.id.cityName);
        state = (EditText) findViewById(R.id.stateName);
        hotel = (TextView) findViewById(R.id.hotels);
        weather = (TextView) findViewById(R.id.weather);
        touchme = (Button) findViewById(R.id.enter);
        restaurant = (TextView) findViewById(R.id.FoodPlaces);
        requestQueue = Volley.newRequestQueue(this);
        touchme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String findplace_url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=" + city.getText().toString() + "%20" + state.getText().toString() + "&inputtype=textquery&fields=geometry&key=" + api_key;

                    Log.d("here", findplace_url);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET,
                            findplace_url,
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(final JSONObject response){
                                    try{
                                        JSONObject abc = response.getJSONArray("candidates").getJSONObject(0);
                                        JSONObject geometry = (JSONObject) abc.get("geometry");
                                        JSONObject location = (JSONObject) geometry.get("location");
                                        latitude = location.getDouble("lat");
                                        longitude = location.getDouble("lng");
                                        Log.d(TAG, abc.get("geometry").toString());

                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();

                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.w(TAG, error.toString());
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                    //hotel.setText(jsonObjectRequest.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    //hotel.setText("Please enter a Valid City");
                    //restaurant.setText("Please enter a Valid City");
                    //weather.setText("Please enter a Valid City");
                }
                String nearbysearch_url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=1500&type=restaurant&keyword=cruise&key=" + api_key;
                Log.d("url", nearbysearch_url);
                try {
                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(
                            Request.Method.GET,
                            nearbysearch_url,
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(final JSONObject response){
                                    try{
                                        JSONObject def = response.getJSONArray("results").getJSONObject(0);
                                        Object rest = def.get("name");
                                        String name = rest.toString();
                                        restaurant.setText(name);
                                        //JSONObject abc = response.getJSONArray("candidates").getJSONObject(0);
                                        //JSONObject geometry = (JSONObject) abc.get("geometry");
                                        //JSONObject location = (JSONObject) geometry.get("location");
                                        //latitude = location.getDouble("lat");
                                        //longitude = location.getDouble("lng");
                                        //restaurant.setText(Double.toString(longitude));

                                        Log.d(TAG, def.get("name").toString());

                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();

                                    }

                                    //hotel.setText(response.toString());
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.w(TAG, error.toString());
                        }
                    });
                    requestQueue.add(jsonObjectRequest1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}