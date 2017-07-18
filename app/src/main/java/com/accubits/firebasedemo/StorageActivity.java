package com.accubits.firebasedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Royce RB on 18/7/17.
 */

public class StorageActivity extends AppCompatActivity {

    ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_storage);
        img = (ImageView) findViewById(R.id.img);

        Picasso.with(this).load("https://firebasestorage.googleapis.com/v0/b/accubits-9c5cc.appspot.com/o/bhakth.png?alt=media&token=df7406ca-5506-40d3-b62f-07fd685e80cc")
                .into(img);
    }
}
