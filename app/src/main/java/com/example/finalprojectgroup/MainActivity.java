package com.example.finalprojectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button login;
    EditText email, password;
    TextView createNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.Login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.mypassreg);
        createNewUser = findViewById(R.id.createnewac);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("TimeLeftBegin", 600000);
        editor.putLong("TimeLeftInter", 600000);
        editor.putLong("TimeLeftAdvance", 600000);
        editor.apply();

        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean status = preferences1.getBoolean("SignedIn", false);
        if (status) {
//            Intent intent = new Intent(MainActivity.this,UserProfile.class);
//            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().equals("") || password.getText().equals("")){
                    Toast.makeText(MainActivity.this, "Email or password is Blank ", Toast.LENGTH_LONG).show();
                    Log.d("Missing", "onClick:  email password is empty check ");
                }else{
                    Intent intent = new Intent(MainActivity.this,UserProfile.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("SignedIn", true);
                    editor.apply();
                    startActivity(intent);
                }

            }
        });

        createNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register_activity.class);
                startActivity(intent);
            }
        });

    }
}