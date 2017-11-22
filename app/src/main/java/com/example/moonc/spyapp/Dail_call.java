package com.example.moonc.spyapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by moonc on 11/15/2017.
 */

public class Dail_call extends Fragment {

    ListView listView;
    ArrayList<Log_Object_creator> arrayList;
    Log_Object_creator log_object_creator;
    log_adapter log_adapter;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dail_layout,container,false);
        listView = (ListView)view.findViewById(R.id.log_list);
        arrayList = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Pushpo").child("Outgoing_calls");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children) {
                    int pos = 0 ;
                    String val = child.getValue().toString();
                      for(int i=0;i<val.length();i++)
                      {
                          if(val.charAt(i)=='@')
                          {
                              if(i!=0) {
                                  pos = i - 1;
                                  break;
                              }
                          }
                      }

                      String number = val.substring(0,pos);
                      String date = val.substring(pos+4,pos+18+4);

                     log_object_creator = new Log_Object_creator(number,date);
                     arrayList.add(log_object_creator);

                }

                log_adapter = new log_adapter(arrayList,getContext(),"dial");
                listView.setAdapter(log_adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        return view;
    }
}
