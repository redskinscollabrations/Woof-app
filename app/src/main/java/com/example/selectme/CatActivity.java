package com.example.newproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CatActivity extends AppCompatActivity {


    EditText cName, cGender, cBreed, cBirthday, cColor;
    Button btn_save, btn_view, btn_update, btn_delete;
    DatabaseReference dbRef;
    Cat cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        cName = findViewById(R.id.et_name);
        cGender = findViewById(R.id.et_gender);
        cBreed = findViewById(R.id.et_breed);
        cBirthday = findViewById(R.id.et_birthday);
        cColor = findViewById(R.id.et_color);

        btn_save = findViewById(R.id.btn_save);
        btn_view = findViewById(R.id.btn_view);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        cat = new Cat();
    }


    //Method to clear all user inputs

            public void clearControls(){

                cName.setText("");
                cGender.setText("");
                cBreed.setText("");
                cBirthday.setText("");
                cColor.setText("");

    }




    //Method to insert cat pet

    public void CreateData(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Cats");

        try {

            if (TextUtils.isEmpty(cName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter your pet's name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(cGender.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter pet's gender", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(cBreed.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter pet's breed", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(cBirthday.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter pet's birthday", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(cColor.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter pet's color", Toast.LENGTH_SHORT).show();
            else
            {

                //Take inputs from the user and assigning them to this instance of the Cat

                cat.setName(cName.getText().toString().trim());
                cat.setGender(cGender.getText().toString().trim());
                cat.setBreed(cBreed.getText().toString().trim());
                cat.setBirthday(cBirthday.getText().toString().trim());
                cat.setColor(cColor.getText().toString().trim());

                //Insert into database

                //dbRef.push().setValue(dog);
                dbRef.child("cat1").setValue(cat);

                //Feedback to the user about successfully data saved, via Toast message

                Toast.makeText(getApplicationContext(), "Data Saved Successfully!", Toast.LENGTH_SHORT).show();

                clearControls();

            }


        }
        catch (Exception e){

            //Feedback to the user about unsuccessfully data saved, via Toast message

            Toast.makeText(getApplicationContext(), "Data Saved Unsuccessfully!", Toast.LENGTH_SHORT).show();

        }

    }



    //Method to view data of cat pet

    public void ViewData(View view){

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Cats").child("cat1");

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.hasChildren()){

                    cName.setText(snapshot.child("name").getValue().toString());
                    cGender.setText(snapshot.child("gender").getValue().toString());
                    cBreed.setText(snapshot.child("breed").getValue().toString());
                    cBirthday.setText(snapshot.child("birthday").getValue().toString());
                    cColor.setText(snapshot.child("color").getValue().toString());

                }

                else

                    //Feedback to the user via Toast message

                    Toast.makeText(getApplicationContext(), "No Data to Display!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    //Method to update data of the cat pet

    public void UpdateData(View view){

        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Cats");

        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild("cat1")){

                    cat.setName(cName.getText().toString().trim());
                    cat.setGender(cGender.getText().toString().trim());
                    cat.setBreed(cBreed.getText().toString().trim());
                    cat.setBirthday(cBirthday.getText().toString().trim());
                    cat.setColor(cColor.getText().toString().trim());

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Cats").child("cat1");

                    dbRef.setValue(cat);

                    clearControls();

                    //Feedback to the user about data successfully updates, via Toast message

                    Toast.makeText(getApplicationContext(), "Data Updated Successfully!", Toast.LENGTH_SHORT).show();

                }

                else

                    //Feedback to the user about data unsuccessfully updated, via Toast message

                    Toast.makeText(getApplicationContext(), "Data Updated Unsuccessfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    //Method to delete cat pet details

    public void DeleteData(View view){

        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Cats");

        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild("cat1")){

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Cats").child("cat1");

                    dbRef.removeValue();

                    clearControls();

                    //Feedback to the user about data deleted successfully via Toast message

                    Toast.makeText(getApplicationContext(), "Data Deleted Successfully!", Toast.LENGTH_SHORT).show();

                }

                else

                    //Feedback to the user about data deleted unsuccessfully via Toast message

                    Toast.makeText(getApplicationContext(), "Data Deleted Unsuccessfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}