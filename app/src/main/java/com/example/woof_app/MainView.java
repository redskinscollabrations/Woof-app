package com.example.woof_app;

import androidx.annotation.Nullable;
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

public class MainView extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    /* Initialized myDB class */
    MyDatabaseHelper myDB;
    /* Creating the array list */
    ArrayList<String> menu_id, menu_name, menu_amount;
    CustomerAdapter customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainView.this, AddMenu.class);
                startActivity(intent);

            }
        });

        myDB = new MyDatabaseHelper(MainView.this);
        menu_id = new ArrayList<>();
        menu_name = new ArrayList<>();
        menu_amount = new ArrayList<>();

        /* Calling the function */
        storeDataInArrays();

        customerAdapter = new CustomerAdapter(MainView.this,MainView.this, menu_id, menu_name, menu_amount);
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainView.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    /* Method Implementation of storeDataInArray */
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                menu_id.add(cursor.getString(0));
                menu_name.add(cursor.getString(1));
                menu_amount.add(cursor.getString(2));
            }
        }
    }


}
