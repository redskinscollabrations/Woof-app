package com.example.main_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{

    private CardView mypets, schedule, professionals, supplies, adopts, resetapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Define Cards
        mypets = findViewById(R.id.card_mypets);
        schedule = findViewById(R.id.card_schedule);
        professionals = findViewById(R.id.card_professionals);
        supplies = findViewById(R.id.card_supplies);
        adopts = findViewById(R.id.card_adopts);
        resetapp = findViewById(R.id.card_resetapp);

        //Add clicklistner to the cards.

        mypets.setOnClickListener(this);
        schedule.setOnClickListener(this);
        professionals.setOnClickListener(this);
        supplies.setOnClickListener(this);
        adopts.setOnClickListener(this);
        resetapp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()){

            case R.id.card_mypets : i = new Intent(this,My_Pets.class);startActivity(i);break;
            case R.id.card_schedule: i = new Intent(this,Schedule.class);startActivity(i);break;
            case R.id.card_professionals: i = new Intent(this,MainActivity.class);startActivity(i);break;
            case R.id.card_supplies: i = new Intent(this,Supplies.class);startActivity(i);break;
            case R.id.card_adopts: i = new Intent(this,Adopts.class);startActivity(i);break;
            case R.id.card_resetapp: i = new Intent(this,ResetApp.class);startActivity(i);break;

        }

    }
}