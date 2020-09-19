package com.example.nazhatafzazain11rpl022019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

public class Detail extends AppCompatActivity {

    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdeskripsi;
    Button btnbookmark;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        extras = getIntent().getExtras();
        tvjudul =  findViewById(R.id.tvjudul);
        tvdeskripsi =  findViewById(R.id.tvdeskripsi);
        ivposter =  findViewById(R.id.ivposter);
        btnbookmark = findViewById(R.id.btnbookmark);
        if (extras != null) {
            title = extras.getString("judul");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            tvjudul.setText(title);
            tvdeskripsi.setText(deskripsi);
            Glide.with(Detail.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
        }
    }
}