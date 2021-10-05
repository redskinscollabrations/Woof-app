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

public class VaccinneEdit extends AppCompatActivity {

    DbCon myDB;
    //String id, task, date;
    EditText V_task, V_id;
    TextView V_date;
    Button woof_updateV, woof_deleteV, btnSetVDate;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinne_edit);

        myDB = new DbCon(this);

        V_task = findViewById(R.id.vac_edittxt);
        V_date = findViewById(R.id.vacdate_edittxt);
        V_id = findViewById(R.id.id_vses);
        woof_updateV = findViewById(R.id.woof_updateV);
        woof_deleteV = findViewById(R.id.woof_deleteV);
        btnSetVDate = findViewById(R.id.btn_set_Vdate);

        btnSetVDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(VaccinneEdit.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                V_date.setText("");
                                V_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        woof_updateV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Boolean update = myDB.vaccineUpdate(V_task.getText().toString(), V_date.getText().toString(), V_id.getText().toString());

                Toast.makeText(VaccinneEdit.this, "result : "+update, Toast.LENGTH_SHORT).show();

                if(update == true){
                    Toast.makeText(VaccinneEdit.this, "successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(VaccinneEdit.this,VaccinneView.class);
                    startActivity(i);
                    finish();
                }else
                {
                    Toast.makeText(VaccinneEdit.this, "Failed", Toast.LENGTH_LONG).show();
                }

            }
        });

        woof_deleteV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean delete = myDB.vaccineDelete(V_id.getText().toString());

                //Toast.makeText(DailySchEdit.this, "result : "+update, Toast.LENGTH_SHORT).show();

                if(delete == true){
                    Toast.makeText(VaccinneEdit.this, "successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(VaccinneEdit.this,VaccinneView.class);
                    startActivity(i);
                    finish();
                }else
                {
                    Toast.makeText(VaccinneEdit.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        getAndsetIntentData();
    }

    void getAndsetIntentData(){
        if(getIntent().hasExtra("vtask") && getIntent().hasExtra("vdate")){
            //getting data from Intent
            String id = getIntent().getStringExtra("vid");
            String task = getIntent().getStringExtra("vtask");
            String date = getIntent().getStringExtra("vdate");

            //setting Intent data
            V_id.setText(id);
            V_task.setText(task);
            V_date.setText(date);
        }
    }
}