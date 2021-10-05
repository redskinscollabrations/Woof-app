package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView myPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        myPets = findViewById(R.id.card_mypets);

        myPets.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()) {

            case R.id.card_mypets:
                i = new Intent(this, petphotoActivity.class);
                startActivity(i);
                break;
        }
    }
}