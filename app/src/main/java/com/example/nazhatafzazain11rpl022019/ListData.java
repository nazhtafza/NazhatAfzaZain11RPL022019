package com.example.nazhatafzazain11rpl022019;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {
    TextView tvnodata;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<Model> DataArrayList; //kit add kan ke adapter
    ImageView tambah_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        dialog = new ProgressDialog(ListData.this);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        tvnodata.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //addData();
        addDataOnline();
    }

    void addData() {
        //offline, isi data offline dulu
        DataArrayList = new ArrayList<>();
        Model data1 = new Model();
        data1.setOriginal_title("Judul Film");
        data1.setPoster_path("https://image.tmdb.org/t/p/w500/k68nPLbIST6NP96JmTxmZijEvCA.jpg");
        data1.setAdult(false);
        data1.setOverview("Deskripsi Film disini");
        data1.setVote_count(100);
        data1.setRelease_date("01-01-2020");
        DataArrayList.add(data1);


        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //get data online


    }
    void addDataOnline(){
        //loading disini
        dialog.setMessage("sedang memproses data");
        dialog.show();

        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=b72f37f4c142a42a17e31b11ac25f3a1")
               .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("hasiljson","onResponse: "+response.toString());
                        DataArrayList  = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku=new Model();
                                modelku.setId(jsonObject.getInt("id"));
                                modelku.setOriginal_title(jsonObject.getString("original_title"));
                                modelku.setRelease_date(jsonObject.getString("release_date"));
                                modelku.setOverview(jsonObject.getString("overview"));
                                modelku.setPoster_path("https://image.tmdb.org/t/p/w500"+jsonObject.getString("poster_path"));
                                modelku.setAdult(jsonObject.getBoolean("adult"));
                                modelku.setVote_count(jsonObject.getInt("vote_count"));
                                DataArrayList.add(modelku);
                            }

                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                   Model movie = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), Detail.class);
                                    intent.putExtra("judul",movie.original_title);
                                    intent.putExtra("date",movie.release_date);
                                    intent.putExtra("deskripsi",movie.overview);
                                    intent.putExtra("path",movie.poster_path);
                                    startActivity(intent);
                                    Toast.makeText(ListData.this, ""+position, Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch ( JSONException e) {
                            e.printStackTrace();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }
                    }



                    @Override
                    public void onError(ANError error) {
                        Log.d("myerror","onError errorcode: "+ error.getErrorCode());
                        Log.d("myerror","onError errorcode: " +error.getErrorBody());
                        Log.d("myerror","onError errorcode: "+ error.getErrorDetail());

                    }
                });


    }
}