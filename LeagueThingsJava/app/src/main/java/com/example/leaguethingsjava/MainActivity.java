package com.example.leaguethingsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.leaguethingsjava.domain.ChampionsDataSource;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChampionsDataSource.fillSet();
        TextView toplaners = (TextView)  findViewById(R.id.top);

        toplaners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toplanersIntent = new Intent(MainActivity.this,ToplanersActivity.class);
                startActivity(toplanersIntent);
            }
        });

        TextView junglers = (TextView)  findViewById(R.id.jungle);

        junglers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent junglersIntent = new Intent(MainActivity.this,JunglersActivity.class);
                startActivity(junglersIntent);
            }
        });

        TextView midlaners = (TextView)  findViewById(R.id.mid);

        midlaners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent midIntent = new Intent(MainActivity.this,MidlanersActivity.class);
                startActivity(midIntent);
            }
        });

        TextView adcs = (TextView)  findViewById(R.id.adc);

        adcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adcsIntent = new Intent(MainActivity.this,ADCsActivity.class);
                startActivity(adcsIntent);
            }
        });

        TextView supports = (TextView)  findViewById(R.id.support);

        supports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportsIntent = new Intent(MainActivity.this,SupportsActivity.class);
                startActivity(supportsIntent);
            }
        });
    }
}