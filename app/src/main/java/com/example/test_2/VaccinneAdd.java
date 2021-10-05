package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class VaccinneAdd extends AppCompatActivity {

    EditText woofVaccinetxt;
    TextView woofVaccineDatetxt;
    Button woofBtnaddVaccine, btnselVdate;
    private int mYear, mMonth, mDay;

    DbCon database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinne_add);
        btnselVdate = findViewById(R.id.btn_sel_Vdate);
        woofVaccinetxt = findViewById(R.id.woof_Vaccinetxt);
        woofVaccineDatetxt = findViewById(R.id.woof_VaccineDatetxt);
        woofBtnaddVaccine = findViewById(R.id.woof_BtnaddVaccine);

        btnselVdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(VaccinneAdd.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                woofVaccineDatetxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        // myDB = new myDatabaseHelper(this);

        database = new DbCon(this);


        woofBtnaddVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = woofVaccinetxt.getText().toString();
                String date = woofVaccineDatetxt.getText().toString();
                //Toast.makeText(DailySchAdd.this, "task : "+task, Toast.LENGTH_SHORT).show();
                //Toast.makeText(DailySchAdd.this, "date : "+date, Toast.LENGTH_SHORT).show();

                //Boolean insert = database.taskAdd(task, date);
                Boolean insert = database.vaccineAdd(woofVaccinetxt.getText().toString(), woofVaccineDatetxt.getText().toString());

                Toast.makeText(VaccinneAdd.this, "result : " + insert, Toast.LENGTH_SHORT).show();

                if (insert == true) {
                    Toast.makeText(VaccinneAdd.this, "successfull", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(VaccinneAdd.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}