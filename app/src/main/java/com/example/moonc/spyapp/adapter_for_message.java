package com.example.moonc.spyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by moonc on 11/15/2017.
 */

public class adapter_for_message extends BaseAdapter {


    ArrayList<Object_creator_for_message> arrayList = new ArrayList<Object_creator_for_message>();
    Context context;
    LayoutInflater inflater;

    TextView number_of_the_user;
    TextView message;
    TextView date;

    public adapter_for_message(ArrayList<Object_creator_for_message> MarrayList, Context context) {
        this.arrayList = MarrayList;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(arrayList.isEmpty())
            Toast.makeText(context,"Empty arraylisss",Toast.LENGTH_SHORT).show();
        else
            return arrayList.size();

        return 0;
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
        view = inflater.inflate(R.layout.message_adapter_, null);
        number_of_the_user = (TextView) view.findViewById(R.id.number);
        message = (TextView) view.findViewById(R.id.message_body);
        date = (TextView)view.findViewById(R.id.date);

        if(arrayList.isEmpty())
        {
            Toast.makeText(context,"Emplty in adapter",Toast.LENGTH_SHORT).show();
        }

        number_of_the_user.setText(arrayList.get(i).getNumber());
        message.setText(arrayList.get(i).getMessage());
        date.setText(arrayList.get(i).getDate());

        return view;
    }

}
