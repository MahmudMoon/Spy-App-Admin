package com.example.moonc.spyapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by moonc on 11/15/2017.
 */

public class Send_Message extends Fragment {

    ListView listView;
    Object_creator_for_message object_creator_for_message;
    ArrayList<Object_creator_for_message> arrayList;
    adapter_for_message adapter ;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_layout,container,false);
        listView = (ListView)view.findViewById(R.id.mlist);
        arrayList = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Pushpo").child("Outgoing_Message");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children) {

                    String val = child.getValue().toString();
                    boolean b = true;

                    int[] pos = new int[2];
                    int j = 0;
                    for(int i=0;i<val.length();i++)
                    {
                        if(val.charAt(i)=='@' && b)
                        {
                            if(i!=0) {

                                pos[j] = i-1;

                            }
                            b = false;
                            j++;

                        }else if(val.charAt(i)!='@')
                        {
                            b = true;
                        }
                    }

                    String number = val.substring(0,pos[0]);
                    String date = val.substring(pos[0]+5,pos[1]);
                    String message = val.substring(pos[1]+6,val.length()-1);

                    object_creator_for_message = new Object_creator_for_message(number,message,date);
                    arrayList.add(object_creator_for_message);

                }

                if(arrayList.isEmpty()){
                    Toast.makeText(getContext(),"Arraylist is emply",Toast.LENGTH_SHORT).show();
                }else {

                    adapter = new adapter_for_message(arrayList, getContext());
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }
}
