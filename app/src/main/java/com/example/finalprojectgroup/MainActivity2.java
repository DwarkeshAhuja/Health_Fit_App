package com.example.finalprojectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    DBHelper DB;
    List<MealDetails> mealDetailsList;
    ListView listView;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.dietName);
        Intent intent = getIntent();
        String nameOfUser = intent.getStringExtra("nameOfUser");
        String premiumPaid = intent.getStringExtra("premiumPaid");
        String userEmail = intent.getStringExtra("userEmail");
        tv.setText(tv.getText().toString() + " " + nameOfUser + " is ");
        mealDetailsList = new ArrayList<>();
        listView = findViewById(R.id.listView);
        DB = new DBHelper(this);
        Cursor res1 = DB.getMealdata(userEmail);

        while(res1.moveToNext()){
            MealDetails meal = new MealDetails(res1.getString(1), res1.getString(2),res1.getString(3),res1.getString(4), res1.getString(5));
            mealDetailsList.add(meal);
        }
        MealAdaptor ma = new MealAdaptor(MainActivity2.this,R.layout.mealdetails,mealDetailsList);
        listView.setAdapter(ma);
    }
}