package com.example.woof_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddMenu extends AppCompatActivity {
    EditText menu_input, amount_input;
    Button btn_add;

    //Variable declaration for Calculator
    EditText unitprice, qty;
    TextView viewprice;
    Button btn_cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        menu_input = findViewById(R.id.menu_input);
        amount_input = findViewById((R.id.amount_input));
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddMenu.this);
                myDB.addmenu(menu_input.getText().toString().trim(),
                       Integer.parseInt(amount_input.getText().toString().trim()));
            }
        });

        //Calculator
        unitprice = findViewById(R.id.unitprice);
        qty = findViewById(R.id.qty);
        viewprice = findViewById(R.id.viewprice);
        btn_cal = findViewById(R.id.btn_cal);
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer no1 = Integer.parseInt(unitprice.getText().toString());
                Integer no2 = Integer.parseInt(qty.getText().toString());

                Integer price = no1*no2;
                viewprice.setText(price.toString());
            }
        });

    }
}