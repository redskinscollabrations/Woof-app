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

public class BirdActivity extends AppCompatActivity {

    EditText bName, bGender, bBreed, bBirthday, bColor;
    Button btn_save, btn_view, btn_update, btn_delete;
    DatabaseReference dbRef;
    Bird bird;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        bName = findViewById(R.id.et_name);
        bGender = findViewById(R.id.et_gender);
        bBreed = findViewById(R.id.et_breed);
        bBirthday = findViewById(R.id.et_birthday);
        bColor = findViewById(R.id.et_color);

        btn_save = findViewById(R.id.btn_save);
        btn_view = findViewById(R.id.btn_view);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        bird = new Bird();

    }



    //Method to clear all user inputs

            public void clearControls(){

                bName.setText("");
                bGender.setText("");
                bBreed.setText("");
                bBirthday.setText("");
                bColor.setText("");

    }



    //Method to insert bird pet

    public void CreateData(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Birds");

        try {

            if (TextUtils.isEmpty(bName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter your pet's name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(bGender.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter pet's gender", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(bBreed.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter pet's breed", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(bBirthday.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter pet's birthday", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(bColor.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter pet's color", Toast.LENGTH_SHORT).show();
            else
            {

                //Take inputs from the user and assigning them to this instance of the Cat

                bird.setName(bName.getText().toString().trim());
                bird.setGender(bGender.getText().toString().trim());
                bird.setBreed(bBreed.getText().toString().trim());
                bird.setBirthday(bBirthday.getText().toString().trim());
                bird.setColor(bColor.getText().toString().trim());

                //Insert into database

                //dbRef.push().setValue(dog);
                dbRef.child("bird1").setValue(bird);

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



    //Method to view data of bird pet

    public void ViewData(View view){

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Birds").child("bird1");

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.hasChildren()){

                    bName.setText(snapshot.child("name").getValue().toString());
                    bGender.setText(snapshot.child("gender").getValue().toString());
                    bBreed.setText(snapshot.child("breed").getValue().toString());
                    bBirthday.setText(snapshot.child("birthday").getValue().toString());
                    bColor.setText(snapshot.child("color").getValue().toString());

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



    //Method to update data of the bird pet

    public void UpdateData(View view){

        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Birds");

        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild("bird1")){

                    bird.setName(bName.getText().toString().trim());
                    bird.setGender(bGender.getText().toString().trim());
                    bird.setBreed(bBreed.getText().toString().trim());
                    bird.setBirthday(bBirthday.getText().toString().trim());
                    bird.setColor(bColor.getText().toString().trim());

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Birds").child("bird1");

                    dbRef.setValue(bird);

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



    //Method to delete bird pet details

    public void DeleteData(View view){

        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Birds");

        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild("bird1")){

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Birds").child("bird1");

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