package com.example.moonc.spyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by moonc on 11/14/2017.
 */

public class popUp extends Activity {

    EditText name;
    Button button;
    Sqlite_helper sqlite_helper;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_layout);
        sqlite_helper = new Sqlite_helper(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width*0.7),(int) (height*0.6));



        name = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Victim_name  = name.getText().toString();
                boolean res = sqlite_helper.add(Victim_name);

                if(res)
                    Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(popUp.this,Show_child.class);

                intent.putExtra("Key",Victim_name);
                startActivity(intent);
            }
        });





    }
}
