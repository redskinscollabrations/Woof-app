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

public class DogActivity extends AppCompatActivity {

    EditText dName, dGender, dBreed, dBirthday, dColor;
    Button btn_save, btn_view, btn_update, btn_delete;
    DatabaseReference dbRef;
    Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);

        dName = findViewById(R.id.et_name);
        dGender = findViewById(R.id.et_gender);
        dBreed = findViewById(R.id.et_breed);
        dBirthday = findViewById(R.id.et_birthday);
        dColor = findViewById(R.id.et_color);

        btn_save = findViewById(R.id.btn_save);
        btn_view = findViewById(R.id.btn_view);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        dog = new Dog();
    }




    //Method to clear all user inputs

        public void clearControls(){

            dName.setText("");
            dGender.setText("");
            dBreed.setText("");
            dBirthday.setText("");
            dColor.setText("");

        }



    //Method to insert dog pet

        public void CreateData(View view){

            dbRef = FirebaseDatabase.getInstance().getReference().child("Dogs");

            try {

                if (TextUtils.isEmpty(dName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter your pet's name", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(dGender.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter pet's gender", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(dBreed.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter pet's breed", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(dBirthday.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter pet's birthday", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(dColor.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter pet's color", Toast.LENGTH_SHORT).show();
                else
                {

                    //Take inputs from the user and assigning them to this instance of the Dog

                    dog.setName(dName.getText().toString().trim());
                    dog.setGender(dGender.getText().toString().trim());
                    dog.setBreed(dBreed.getText().toString().trim());
                    dog.setBirthday(dBirthday.getText().toString().trim());
                    dog.setColor(dColor.getText().toString().trim());

                    //Insert into database

                    //dbRef.push().setValue(dog);
                    dbRef.child("dog1").setValue(dog);

                    //Feedback to the user via Toast message

                    Toast.makeText(getApplicationContext(), "Data Saved Successfully!", Toast.LENGTH_SHORT).show();

                    clearControls();

                }


            }
            catch (Exception e){

                //Feedback to the user via Toast message

                Toast.makeText(getApplicationContext(), "Data Saved Unsuccessfully!", Toast.LENGTH_SHORT).show();

            }

        }



    //Method to view data of dog pet

        public void ViewData(View view){

            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Dogs").child("dog1");

            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    if (snapshot.hasChildren()){

                        dName.setText(snapshot.child("name").getValue().toString());
                        dGender.setText(snapshot.child("gender").getValue().toString());
                        dBreed.setText(snapshot.child("breed").getValue().toString());
                        dBirthday.setText(snapshot.child("birthday").getValue().toString());
                        dColor.setText(snapshot.child("color").getValue().toString());

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



    //Method to update data of the dog pet

        public void UpdateData(View view){

            DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Dogs");

            updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.hasChild("dog1")){

                        dog.setName(dName.getText().toString().trim());
                        dog.setGender(dGender.getText().toString().trim());
                        dog.setBreed(dBreed.getText().toString().trim());
                        dog.setBirthday(dBirthday.getText().toString().trim());
                        dog.setColor(dColor.getText().toString().trim());

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Dogs").child("dog1");

                        dbRef.setValue(dog);

                        clearControls();

                        //Feedback to the user via Toast message

                        Toast.makeText(getApplicationContext(), "Data Updated Successfully!", Toast.LENGTH_SHORT).show();

                    }

                    else

                        //Feedback to the user via Toast message

                        Toast.makeText(getApplicationContext(), "Data Updated Unsuccessfully!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }



    //Method to delete dog pet details

        public void DeleteData(View view){

            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Dogs");

            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.hasChild("dog1")){

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Dogs").child("dog1");

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