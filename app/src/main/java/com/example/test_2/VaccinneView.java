package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VaccinneView extends AppCompatActivity {

    RecyclerView recycleViewVaccinne;
    FloatingActionButton vaccinebtn_add;

    DbCon mydb;
    ArrayList<String> Vaccine_id, Vaccine_task, Vaccine_date;
    CustomAdapter3 customAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinne_view);

        recycleViewVaccinne = findViewById(R.id.recycleViewVaccinne);
        vaccinebtn_add = findViewById(R.id.vaccinebtn_add);
        vaccinebtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VaccinneView.this, VaccinneAdd.class);
                startActivity(intent);
            }
        });

        mydb = new DbCon(this);
        Vaccine_id = new ArrayList<>();
        Vaccine_task = new ArrayList<>();
        Vaccine_date = new ArrayList<>();

        storeDataInArray();

        customAdapter3 = new CustomAdapter3(VaccinneView.this, Vaccine_id, Vaccine_task, Vaccine_date);
        recycleViewVaccinne.setAdapter(customAdapter3);
        recycleViewVaccinne.setLayoutManager(new LinearLayoutManager(VaccinneView.this));
    }

    void storeDataInArray(){
        Cursor cursor = mydb.vaccinereadData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                Vaccine_id.add(cursor.getString(0));
                Vaccine_task.add(cursor.getString(1));
                Vaccine_date.add(cursor.getString(2));
            }
        }
    }

}