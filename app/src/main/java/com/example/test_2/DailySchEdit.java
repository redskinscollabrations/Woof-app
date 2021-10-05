package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DailySchEdit extends AppCompatActivity {

    DbCon myDB;
    //String id, task, date;
    EditText d_task, d_id;
    TextView d_date;
    Button woof_updateD, d_delete, btnSetDate;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sch_edit);

        myDB = new DbCon(this);

        d_task = findViewById(R.id.task_edittxt);
        d_date = findViewById(R.id.date_edittxt);
        d_id = findViewById(R.id.id_ses);
        woof_updateD = findViewById(R.id.woof_updateD);
        d_delete = findViewById(R.id.woof_deleteD);
        btnSetDate = findViewById(R.id.btn_set_date);

        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(DailySchEdit.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                d_date.setText("");
                                d_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        woof_updateD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Boolean update = myDB.taskUpdate(d_task.getText().toString(), d_date.getText().toString(), d_id.getText().toString());

                Toast.makeText(DailySchEdit.this, "result : "+update, Toast.LENGTH_SHORT).show();

                if(update == true){
                    Toast.makeText(DailySchEdit.this, "successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(DailySchEdit.this,DailySchView.class);
                    startActivity(i);
                    finish();
                }else
                {
                    Toast.makeText(DailySchEdit.this, "Failed", Toast.LENGTH_LONG).show();
                }

            }
        });

        d_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean delete = myDB.taskDelete(d_id.getText().toString());

                //Toast.makeText(DailySchEdit.this, "result : "+update, Toast.LENGTH_SHORT).show();

                if(delete == true){
                    Toast.makeText(DailySchEdit.this, "successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(DailySchEdit.this,DailySchView.class);
                    startActivity(i);
                    finish();
                }else
                {
                    Toast.makeText(DailySchEdit.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        //myDB.UpdateDetails(id,task, date);
        //myDB.DeleteDetails(id);
        getAndsetIntentData();
    }

    void getAndsetIntentData(){
        if(getIntent().hasExtra("task") && getIntent().hasExtra("date")){
            //getting data from Intent
            String id = getIntent().getStringExtra("id");
            String task = getIntent().getStringExtra("task");
            String date = getIntent().getStringExtra("date");

            //setting Intent data
            d_id.setText(id);
            d_task.setText(task);
            d_date.setText(date);
        }
    }
}