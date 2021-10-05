package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button woof_btnDaily;
    Button woof_btnVaccin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        woof_btnDaily = findViewById(R.id.woof_btnDaily);
        woof_btnDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, DailySchView.class);
                startActivity(intent);
            }
        });

        woof_btnVaccin = findViewById(R.id.woof_btnVaccin);
        woof_btnVaccin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, VaccinneView.class);
                startActivity(intent);
            }
        });
    }
}