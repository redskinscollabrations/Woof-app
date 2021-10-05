package com.example.woof_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMenu extends AppCompatActivity {

    EditText menu_input, amount_input;
    Button btn_update, btn_delete;

    String id, menu, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);

        menu_input = findViewById(R.id.menu_input2);
        amount_input = findViewById(R.id.amount_input2);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        //call the method
        getAndSetIntentData();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String updatedmenu = menu_input.getText().toString();
                String updatedamount = amount_input.getText().toString();

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateMenu.this);
                Boolean update = myDB.updateData(id, updatedmenu, updatedamount);
                if (update==false){

                    Toast.makeText(UpdateMenu.this, "Failed to update", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdateMenu.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateMenu.this, MainView.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateMenu.this);
                Boolean delete = myDB.deleteData(id);
                if (delete==false){

                    Toast.makeText(UpdateMenu.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdateMenu.this, "delete Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateMenu.this, MainView.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("menu") &&
                getIntent().hasExtra("amount")) {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            menu = getIntent().getStringExtra("menu");
            amount = getIntent().getStringExtra("amount");

            //setting intent data
            menu_input.setText(menu);
            amount_input.setText(amount);

        } else {
            Toast.makeText(this, "No Data to Intent", Toast.LENGTH_SHORT).show();
        }

    }
}