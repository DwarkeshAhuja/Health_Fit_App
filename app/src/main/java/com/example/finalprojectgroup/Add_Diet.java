package com.example.finalprojectgroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Diet extends AppCompatActivity {

    EditText day, breakfast, lunch, snacks, dinner;
    Button insert, update, delete, view;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diet);

        breakfast = findViewById(R.id.breakfast);
        lunch = findViewById(R.id.lunch);
        day = findViewById(R.id.day);
        snacks = findViewById(R.id.snacks);
        dinner = findViewById(R.id.dinner);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        Intent intent = getIntent();
        String nameOfUser = intent.getStringExtra("nameOfUser");
        String premiumPaid = intent.getStringExtra("premiumPaid");
        String userEmail = intent.getStringExtra("userEmail");

        Toast.makeText(this, "The Email ID is " + userEmail, Toast.LENGTH_LONG).show();



        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String days = day.getText().toString();
                String breakfasts = breakfast.getText().toString();
                String lunchs = lunch.getText().toString();
                String snack = snacks.getText().toString();
                String dinners = dinner.getText().toString();

                Toast.makeText(Add_Diet.this, days + " " + breakfasts + " " + lunchs + " " + snack + " " + dinners, Toast.LENGTH_LONG).show();
                Log.d("login", "onClick: " + days + " " + breakfasts + " " + lunchs + " " + snack + " " + dinners);
                Boolean checkinsertData = DB.insertuserMealdata(userEmail,days,breakfasts,lunchs,snack,dinners);

                if(checkinsertData){
                    Toast.makeText(Add_Diet.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    Log.d("login", "onClick: Data inserted ");
                }
                else {
                    Toast.makeText(Add_Diet.this, "New Entry NOT Inserted", Toast.LENGTH_SHORT).show();
                    Log.d("login", "onClick: Data cannot be inserted ");
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String days = day.getText().toString();
                String breakfasts = breakfast.getText().toString();
                String lunchs = lunch.getText().toString();
                String snack = snacks.getText().toString();
                String dinners = dinner.getText().toString();

                Boolean checkupdate = DB.updateuserMealdata(userEmail,days,breakfasts,lunchs,snack,dinners);

                if(checkupdate){
                    Toast.makeText(Add_Diet.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Add_Diet.this, "Entry not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });


        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                // Intent intent = new Intent(MainActivity.this,MainActivity2.class);


                Cursor res = DB.getMealdata(userEmail);
                StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Email: "+res.getString(0)+"\n");
                        buffer.append("Day: "+res.getString(1)+"\n");
                        buffer.append("Breakfast: "+res.getString(2)+"\n");
                        buffer.append("Lunch: "+res.getString(3)+"\n");
                        buffer.append("Snacks: "+res.getString(4)+"\n");
                        buffer.append("Dinner: "+res.getString(5)+"\n\n\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_Diet.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();
//                if(res.getCount() == 0){
//                    Toast.makeText(MainActivity.this, "Nothing existed!", Toast.LENGTH_SHORT).show();
//
//                }
//                else {
//
//                    startActivity(intent);
////                    StringBuffer buffer = new StringBuffer();
////
////                    while(res.moveToNext()){
////                        buffer.append("Name: "+res.getString(0)+"\n");
////                        buffer.append("Content: "+res.getString(1)+"\n");
////                        buffer.append("Date of Birth: "+res.getString(2)+"\n\n\n");
////                    }
////                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
////                    builder.setCancelable(true);
////                    builder.setTitle("Results");
////                    builder.setMessage(buffer.toString());
////                    builder.show();
//                }
            }
        });

    }
}