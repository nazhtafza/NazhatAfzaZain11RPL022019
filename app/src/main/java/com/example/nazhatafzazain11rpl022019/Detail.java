package com.example.nazhatafzazain11rpl022019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class Detail extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;


    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;
    String id;

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
            id = extras.getString("id");
            tvjudul.setText(title);
            tvdeskripsi.setText(deskripsi);
            Glide.with(Detail.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
        }
        //Set up Realm
        Realm.init(Detail.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movieModel = new ModelMovieRealm();
                movieModel.setDesc(deskripsi);
                movieModel.setJudul(title);
                movieModel.setPath(path);
                movieModel.setReleaseDate(date);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);
            }
        });


    }
}