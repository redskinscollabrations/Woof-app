package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
//import android.widget.TextView;

public class DailySchAdd extends AppCompatActivity {

    //declare the wigets variables
    //myDatabaseHelper myDB;
    EditText woofTasktxt;
    TextView woofDatetxt;
    Button woofBtnaddtask, btnSelDate;
    private int mYear, mMonth, mDay;

    DbCon database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sch_add);

        btnSelDate = findViewById(R.id.btn_sel_date);
        woofTasktxt = findViewById(R.id.woof_Tasktxt);
        woofDatetxt = findViewById(R.id.woof_Datetxt);
        woofBtnaddtask = findViewById(R.id.woof_Btnaddtask);

        btnSelDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(DailySchAdd.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                woofDatetxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

       // myDB = new myDatabaseHelper(this);

        database = new DbCon(this);


        woofBtnaddtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = woofTasktxt.getText().toString();
                String date = woofDatetxt.getText().toString();
                //Toast.makeText(DailySchAdd.this, "task : "+task, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DailySchAdd.this, "date : "+date, Toast.LENGTH_SHORT).show();

                //Boolean insert = database.taskAdd(task, date);
                Boolean insert = database.taskAdd(woofTasktxt.getText().toString(), woofDatetxt.getText().toString());

                Toast.makeText(DailySchAdd.this, "result : "+insert, Toast.LENGTH_SHORT).show();

                if(insert == true){
                    Toast.makeText(DailySchAdd.this, "successfull", Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(DailySchAdd.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        //addData(); // call the addData method to run when button is clicked
    }

/*    //method to add data to the database
    public void addData(){
       woofBtnaddtask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean insert = myDB.taskAdd(woofTasktxt.getText().toString(),
                                woofDatetxt.getText().toString());

                        if(isInserted == true){
                            Toast.makeText(DailySchAdd.this, "successfull", Toast.LENGTH_LONG).show();
                        }else
                        {
                            Toast.makeText(DailySchAdd.this, "Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );


        *//*Intent intent = new Intent(DailySchAdd.this, DailySchEdit.class);
        startActivity(intent);*//*
    }*/



}