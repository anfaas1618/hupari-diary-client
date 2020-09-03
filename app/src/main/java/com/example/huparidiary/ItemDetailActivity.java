package com.example.huparidiary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huparidiary.model.items;
import com.example.huparidiary.network.ItemsJson;
import com.example.huparidiary.network.LocationJson;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import static android.Manifest.permission.CALL_PHONE;

public class ItemDetailActivity extends AppCompatActivity {
  TextView name,rank,address;
  ImageView image;
  Button contactBtn,locationBtn;
  RatingBar ratingBar;
    String number;
    items item;
    int PLACE_PICKER_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        name=findViewById(R.id.item_name_final);
        rank=findViewById(R.id.rank_final);
        address=findViewById(R.id.address_final);
        image=findViewById(R.id.item_image);
        contactBtn=findViewById(R.id.contact);
        locationBtn=findViewById(R.id.btnlocation);
        ratingBar=findViewById(R.id.rating);

        // To retrieve object in second Activity
     item=  (items)  getIntent().getSerializableExtra("MyClass");
    name.setText(item.getName());
    rank.setText(item.getRanks());
    address.setText(item.getAddress());
    Picasso.get().load(item.getImage()).into(image);
    ratingBar.setRating(Float.parseFloat(item.getStars()));
        number=item.getPhone().trim();
      contactBtn.setOnClickListener(new View.OnClickListener() {
          @RequiresApi(api = Build.VERSION_CODES.M)
          @Override
          public void onClick(View view) {
              Intent callIntent = new Intent(Intent.ACTION_CALL);
              callIntent.setData(Uri.parse("tel:"+number));
//              startActivity(callIntent);
              if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                  startActivity(callIntent);
              } else {
                  requestPermissions(new String[]{CALL_PHONE}, 1);
              }

          }
      });
      locationBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              RequestQueue queue = Volley.newRequestQueue(ItemDetailActivity.this);
              String url="https://mibtechnologies.in/hupariapp/getlocation.php?catnamewa="+item.getCatnamewas()+"&name="+item.getName();
              StringRequest request=new StringRequest(url, new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                     String realjson= response.replace("[","").replace("]","");
                      GsonBuilder gsonBuilder=new GsonBuilder();
                      Gson gson =gsonBuilder.create();
                      LocationJson locationJson=gson.fromJson(realjson,LocationJson.class);
                      Log.d("TAG", "onResponse: "+locationJson.getLatitude());
                      Float latitude=Float.parseFloat(locationJson.getLatitude());
                      Float longitude=Float.parseFloat(locationJson.getLongitude());
                      Log.d("TAG", "onResponse: "+locationJson.getLongitude());
                      String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                      startActivity(intent);
                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                      Log.e("TAG", "onErrorResponse:",error );
                  }
              });



              queue.add(request);
          }
      });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getLatLng());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

            }
        }
    }

}