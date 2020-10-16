package com.example.nazhatafzazain11rpl022019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFvourite extends AppCompatActivity {


    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;
    String id;

    TextView tvjudulFav;
    ImageView ivposterFav;
    TextView tvdeskripsiFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fvourite);
        extras = getIntent().getExtras();
        tvjudulFav =  findViewById(R.id.tvjudulFav);
        tvdeskripsiFav =  findViewById(R.id.tvdeskripsiFav);
        ivposterFav=  findViewById(R.id.ivposterFav);
        if (extras != null) {
            title = extras.getString("judul");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            id = extras.getString("id");
            tvjudulFav.setText(title);
            tvdeskripsiFav.setText(deskripsi);
            Glide.with(DetailFvourite.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposterFav);
        }
    }

}