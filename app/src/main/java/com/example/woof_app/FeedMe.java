package com.example.woof_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeedMe extends AppCompatActivity {
    private Button btn_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_me);

        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openViewMeal();
            }
        });
    }
    public void openViewMeal(){
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
    }

}

