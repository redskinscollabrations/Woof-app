package com.example.main_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class adddata extends AppCompatActivity {

    EditText vetname, vet_type, address, city, mobile, purl;
    Button submit, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

        vetname=(EditText)findViewById(R.id.edittxt_vetname);
        vet_type=(EditText)findViewById(R.id.edittxt_vettype);
        address=(EditText)findViewById(R.id.edittxt_address);
        city=(EditText)findViewById(R.id.edittxt_city);
        mobile=(EditText)findViewById(R.id.edittxt_mobile);
        //purl=(EditText)findViewById(R.id.edittxt_url);

        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert();

            }
        });

    }

    private void processinsert() {

        Map<String,Object> map=new HashMap<>();
        map.put("vetname",vetname.getText().toString());
        map.put("vet_type",vet_type.getText().toString());
        map.put("address",address.getText().toString());
        map.put("city",city.getText().toString());
        map.put("mobile",mobile.getText().toString());
        //map.put("purl",purl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Vet").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        vetname.setText("");
                        vet_type.setText("");
                        address.setText("");
                        city.setText("");
                        mobile.setText("");
                        purl.setText("");

                        Toast.makeText(getApplicationContext(), "Added Professionals Details", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(), "Could not Added", Toast.LENGTH_SHORT).show();

                    }
                });


    }
}