package com.example.woof_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_Launch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Launch = (Button) findViewById(R.id.btn_Launch);
        btn_Launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFeedme();
            }
        });
    }
    public void openFeedme(){
        Intent intent = new Intent(this, FeedMe.class);
        startActivity(intent);
    }
}