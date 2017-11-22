package com.example.moonc.spyapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by moonc on 11/14/2017.
 */

public class contacts extends Fragment {


    ArrayList<Contacts_Objects> arrayList;
    Contacts_Objects contacts_objects;
    ListView listView;
    Adapter_for_contacts adapter_for_contacts;
    ProgressBar progressBar;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contactslayout,container,false);
        Toast.makeText(getContext(),"onContacts",Toast.LENGTH_SHORT).show();
         arrayList = new ArrayList<>();
         listView = view.findViewById(R.id.contactslist);
        progressBar = view.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Pushpo").child("Contacts");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> child = dataSnapshot.getChildren();
                for (DataSnapshot ch:child) {
                    String value = ch.getValue().toString();
                    int pos = 0;

                    for(int i=0;i<value.length();i++)
                    {
                        if(value.charAt(i)=='@')
                        {
                            pos = i-1;
                            break;
                        }
                    }

                    String name  = value.substring(0,pos);
                    String number = value.substring(pos+5,value.length()-1);

                    contacts_objects = new Contacts_Objects(name,number);
                    arrayList.add(contacts_objects);

                }

                if(arrayList.isEmpty())
                    Toast.makeText(getContext(),"Empty",Toast.LENGTH_SHORT).show();


                adapter_for_contacts = new Adapter_for_contacts(arrayList,getContext());
                listView.setAdapter(adapter_for_contacts);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        progressBar.setVisibility(View.GONE);




        //Adapter_for_contacts adapter_for_contacts = new Adapter_for_contacts(arrayList,getContext());



        /*databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> all_contacts = dataSnapshot.getChildren();
                for (DataSnapshot con:all_contacts) {

                   String contacts_detail =  con.getValue().toString();
                    Toast.makeText(getContext(),contacts_detail,Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

*/


        return view;
    }
}
