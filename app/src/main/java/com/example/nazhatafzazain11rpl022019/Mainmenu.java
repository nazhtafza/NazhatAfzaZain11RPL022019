package com.example.nazhatafzazain11rpl022019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class Mainmenu extends AppCompatActivity {
        CardView menu1;
        CardView menu2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        menu1=(CardView)findViewById(R.id.menu1);
        final LoadingDialog loadingDialog = new LoadingDialog(Mainmenu.this);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListData.class));
                loadingDialog.startLoadingDialog();
            }
        });
        menu2 = (CardView)findViewById(R.id.menu2);
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListDataFavourite.class));
            }
        });
    }
}