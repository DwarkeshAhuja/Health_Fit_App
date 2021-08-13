package com.example.finalprojectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    ImageView iv1, iv2, iv3, menu;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        iv1 = findViewById(R.id.begin);
        iv2 = findViewById(R.id.inter);
        iv3 = findViewById(R.id.advance);
        menu = findViewById(R.id.menu);
        tv = findViewById(R.id.Name);
        Intent intent = getIntent();
        String nameOfUser = intent.getStringExtra("nameOfUser");
        String premiumPaid = intent.getStringExtra("premiumPaid");
        String userEmail = intent.getStringExtra("userEmail");
        tv.setText(tv.getText()+ " " + nameOfUser + "!");
        Toast.makeText(this, "You have premium " + premiumPaid , Toast.LENGTH_LONG).show();
        if(premiumPaid.equals("true")){
            menu.setVisibility(View.VISIBLE);
        }else{
            menu.setVisibility(View.INVISIBLE);
        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Add_Diet.class);
                intent.putExtra("nameOfUser", nameOfUser);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("premiumPaid", premiumPaid);
                startActivity(intent);
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Exercise.class);
                intent.putExtra("Exercise_Type", "Beginner");
                intent.putExtra("nameOfUser", nameOfUser);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("premiumPaid", premiumPaid);
                startActivity(intent);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Exercise.class);
                intent.putExtra("Exercise_Type", "Intermediate");
                intent.putExtra("nameOfUser", nameOfUser);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("premiumPaid", premiumPaid);
                startActivity(intent);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Exercise.class);
                intent.putExtra("Exercise_Type", "Advance");
                intent.putExtra("nameOfUser", nameOfUser);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("premiumPaid", premiumPaid);
                startActivity(intent);
            }
        });
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean status = preferences1.getBoolean("SignedIn", false);
        if (status) {
            finishAffinity();
        }
    }
}