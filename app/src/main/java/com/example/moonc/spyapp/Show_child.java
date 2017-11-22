package com.example.moonc.spyapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Show_child extends AppCompatActivity {

    ListView listView;
    ArrayList<String>  arrayList;
    Sqlite_helper sqlite_helper;
    String new_Victim ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_child);
        listView = (ListView)findViewById(R.id.listView);
        arrayList = new ArrayList<String>();

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            String data = extras.getString("Key");
            arrayList.add(data);
        }


        sqlite_helper = new Sqlite_helper(this);
        Cursor cursor = sqlite_helper.show();
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String Victim_name  = cursor.getString(0);
                arrayList.add(Victim_name);
            }
        }



        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Show_child.this,MainActivity.class);
                String VictimName = arrayList.get(i);
                intent.putExtra("Victim_name",VictimName);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add)
        {
            Intent intent = new Intent(Show_child.this,popUp.class);
            startActivity(intent);
        }

        if(id==R.id.update)
        {
            Intent intent = new Intent(Show_child.this,Show_child.class);
            startActivity(intent);
        }

        return true;
    }
}
