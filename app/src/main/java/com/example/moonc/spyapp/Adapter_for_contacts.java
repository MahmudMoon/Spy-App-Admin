package com.example.moonc.spyapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by moonc on 11/14/2017.
 */

public class Adapter_for_contacts extends BaseAdapter implements View.OnClickListener{

    ArrayList<Contacts_Objects> arrayList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    TextView name;
    TextView number;
    Button call;
   String number_ = "1234";
    public Adapter_for_contacts(ArrayList<Contacts_Objects> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.contacts_adapter, null);
        name = (TextView) view.findViewById(R.id.textView);
        number = (TextView) view.findViewById(R.id.textView2);
        call = (Button) view.findViewById(R.id.button2);

        if(arrayList.isEmpty())
        {
            Toast.makeText(context,"Emplty in adapter",Toast.LENGTH_SHORT).show();
        }

       name.setText(arrayList.get(i).getName());
        number.setText(arrayList.get(i).getNumber());

        number_ = arrayList.get(i).getNumber();

        call.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context,number_,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number_));

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Toast.makeText(context,"Give Phone Call Permission",Toast.LENGTH_SHORT).show();
            Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
            return;
        }
        context.startActivity(intent);
    }
}
