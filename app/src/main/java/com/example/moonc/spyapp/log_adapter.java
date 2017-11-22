package com.example.moonc.spyapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.moonc.spyapp.R.drawable.dial;

/**
 * Created by moonc on 11/15/2017.
 */

public class log_adapter extends BaseAdapter {

    ArrayList<Log_Object_creator> arrayList;
    Context context;
    LayoutInflater inflater;
    TextView number, date;
    Button call;
    String mLog;

    public log_adapter(ArrayList<Log_Object_creator> arrayList, Context context,String log) {
        this.arrayList = arrayList;
        this.context = context;
        mLog = log;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

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

  //  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.contacts_adapter, null);
        number = (TextView) view.findViewById(R.id.textView);
        date = (TextView) view.findViewById(R.id.textView2);
        call = (Button) view.findViewById(R.id.button2);

        if(mLog.equals("dial"))
        {
            Toast.makeText(context,"dail",Toast.LENGTH_SHORT).show();
             call.setBackgroundResource(R.drawable.dial);
        }else if(mLog.equals("miss"))
        {
            call.setBackgroundResource(R.drawable.missed);
        }else if(mLog.equals("rcv"))
        {
            call.setBackgroundResource(dial);
        }

        number.setText(arrayList.get(i).getNumber());
        date.setText(arrayList.get(i).getTime());
        final String number = arrayList.get(i).getNumber();
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
             }
         });


        return view;
    }
}
