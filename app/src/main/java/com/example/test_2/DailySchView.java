package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DailySchView extends AppCompatActivity {

    RecyclerView recyclerViewDaily;
    FloatingActionButton woof_btnAdd;

    DbCon mydb;
    ArrayList<String> Daily_id, Daily_task, Daily_date;
    CustomAdapter customAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sch_view);

        recyclerViewDaily = findViewById(R.id.recycleViewDaily);
        woof_btnAdd = findViewById(R.id.woof_btnAdd);
        woof_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DailySchView.this, DailySchAdd.class);
                startActivity(intent);
            }
        });

        mydb = new DbCon(this);
        Daily_id = new ArrayList<>();
        Daily_task = new ArrayList<>();
        Daily_date = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(DailySchView.this, Daily_id, Daily_task, Daily_date);
        recyclerViewDaily.setAdapter(customAdapter);
        recyclerViewDaily.setLayoutManager(new LinearLayoutManager(DailySchView.this));



    }

    void storeDataInArray(){
        Cursor cursor = mydb.readallData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                Daily_id.add(cursor.getString(0));
                Daily_task.add(cursor.getString(1));
                Daily_date.add(cursor.getString(2));
            }
        }
    }

}