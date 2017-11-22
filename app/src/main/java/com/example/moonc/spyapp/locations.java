package com.example.moonc.spyapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by moonc on 11/14/2017.
 */

public class locations extends Fragment implements OnMapReadyCallback {

    GoogleMap Map;
    Marker marker = null;


    public static locations newInstance() {
        locations fragment = new locations();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.locationlayout,container,false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Map = googleMap;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Pushpo").child("Location");

          databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Double l1 = 24.365846666666666;
                Double l2 = 88.62233999999998;
               String test = dataSnapshot.getValue().toString();
                int postition_1,position_2,position_3;
                boolean b = true;
                int postition = 0;
                int postition1 = 1;
                int postition2 = 2;
                int[] pos = new int[2];
                int j = 0;
               for(int i = 0; i <test.length();i++)
               {
                  if(test.charAt(i)=='@' && b)
                  {
                      pos[j] = i-1;
                      j++;
                      b = false;
                  }else if(test.charAt(i)!='@')
                  {
                      b = true;
                  }
               }

              String lon =  test.substring(0,pos[0]+1);
               String lat = test.substring(pos[0]+5,pos[1]+1);
               String date = test.substring(pos[1]+4,test.length()-1);


               Toast.makeText(getContext(),lat+"\n"+ lon + "\n" + date,Toast.LENGTH_SHORT).show();

               Double latitude = Double.parseDouble(lat);
               Double longitude = Double.parseDouble(lon);

               if(marker!=null)
                   marker.remove();

                LatLng latLng = new LatLng(latitude,longitude);

                 marker  =  Map.addMarker(new MarkerOptions().position(latLng).title(date));
                  Map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16.0f));

               // Toast.makeText(getContext(),lon+ " "+lat + " " + date ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
