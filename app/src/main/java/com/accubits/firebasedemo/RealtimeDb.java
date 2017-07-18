package com.accubits.firebasedemo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.plus.model.people.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Royce RB on 13/7/17.
 */

public class RealtimeDb extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference databaseReference;
    private ArrayList<CustomObject> customObjects = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_realtime);
        listView = (ListView) findViewById(R.id.list);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setAdapter(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setAdapter(Object value) {
//        List<HashMap<String,String>>
        Log.i("RESP",value.getClass()+"");
        HashMap<String,HashMap<String,String>> map = (HashMap<String, HashMap<String, String>>) value;
        Log.i("RESP",map.size()+"");
        customObjects.add(new CustomObject());
        for(String key:map.keySet()) {
            Log.i("RESP" + key, map.get(key) + "");
            customObjects.add(new CustomObject(map.get(key)));
        }

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    private class CustomAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public CustomAdapter() {
            layoutInflater = RealtimeDb.this.getLayoutInflater();
        }

        @Override
        public int getCount() {
            return customObjects.size();
        }

        @Override
        public Object getItem(int position) {
            return customObjects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder viewHolder;

            if(convertView==null){

                // inflate the layout
                convertView = layoutInflater.inflate(R.layout.row_realtime_db, parent, false);

                // well set up the ViewHolder
                viewHolder = new Holder();
                viewHolder.email = (TextView) convertView.findViewById(R.id.email);
                viewHolder.login = (TextView) convertView.findViewById(R.id.login);
                viewHolder.logout = (TextView) convertView.findViewById(R.id.logout);

                // store the holder with the view.
                convertView.setTag(viewHolder);

            }else{
                // we've just avoided calling findViewById() on resource everytime
                // just use the viewHolder
                viewHolder = (Holder) convertView.getTag();
            }

            // object item based on the position
            CustomObject objectItem = (CustomObject) getItem(position);

            // assign values if the object is not null
            if(objectItem != null) {
                // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
                viewHolder.email.setText(objectItem.getEmail());
                viewHolder.logout.setText(objectItem.getLogout());
                Log.i("RESP",objectItem.getLogin()+":"+objectItem.getLogout());
                viewHolder.login.setText(objectItem.getLogin());
                if(position==0) {
                    viewHolder.email.setTypeface(null, Typeface.BOLD);
                    viewHolder.login.setTypeface(null, Typeface.BOLD);
                    viewHolder.logout.setTypeface(null, Typeface.BOLD);
                }
            }

            return convertView;

        }


    }

    static class Holder {
        TextView email,login,logout;

    }
}
