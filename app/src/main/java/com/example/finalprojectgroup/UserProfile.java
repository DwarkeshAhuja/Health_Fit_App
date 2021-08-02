package com.example.finalprojectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

public class UserProfile extends AppCompatActivity {

    ImageView iv1, iv2, iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        iv1 = findViewById(R.id.begin);
        iv2 = findViewById(R.id.inter);
        iv3 = findViewById(R.id.advance);


        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Exercise.class);
                intent.putExtra("Exercise_Type", "Beginner");
                startActivity(intent);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Exercise.class);
                intent.putExtra("Exercise_Type", "Intermediate");
                startActivity(intent);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Exercise.class);
                intent.putExtra("Exercise_Type", "Advance");
                startActivity(intent);
            }
        });
    }


//    public void onClickImage(View v){
//        int img = (Integer) v.getTag();
//        Intent intent = new Intent(UserProfile.this, Exercise.class);
//        switch (img){
//            case 0:
//                intent.putExtra("Exercise_Type", "Beginner");
//            case 1:
//                intent.putExtra("Exercise_Type", "Intermediate");
//            case 2:
//                intent.putExtra("Exercise_Type", "Advance");
//        }
//        startActivity(intent);
//
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean status = preferences1.getBoolean("SignedIn", false);
        if (status) {
            finishAffinity();
        }
    }
}