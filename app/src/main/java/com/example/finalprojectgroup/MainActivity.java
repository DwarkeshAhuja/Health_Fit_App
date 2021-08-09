package com.example.finalprojectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);
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

                String myEmail = String.valueOf(email.getText());
                String mypass = String.valueOf(password.getText());
                String Password = "";
                String logInUser = "";
                String userEmail = "";
                String premiumPaid = "";
                Cursor cursor = DB.getdata(myEmail);
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    Password = cursor.getString(2);
                    logInUser = cursor.getString(1);
                    userEmail = cursor.getString(0);
                    premiumPaid = cursor.getString(3);

                    Log.d("login", "onClick: " + cursor.getString(0) + " "
                                + cursor.getString(1) + " "
                                + cursor.getString(2) + " "
                                + cursor.getString(3) + " ");

                }else{
                    Toast.makeText(MainActivity.this, myEmail + " ID is not registered", Toast.LENGTH_LONG).show();
                }

                if(email.getText().equals("") || password.getText().equals("")){
                    Toast.makeText(MainActivity.this, "Email or password is Blank ", Toast.LENGTH_LONG).show();
                    Log.d("Missing", "onClick:  email password is empty check ");
                }else{
                    if(mypass.equals(Password)) {
                        Intent intent = new Intent(MainActivity.this, UserProfile.class);
                        intent.putExtra("nameOfUser", logInUser);
                        intent.putExtra("userEmail", userEmail);
                        intent.putExtra("premiumPaid", premiumPaid);
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("SignedIn", true);
                        editor.apply();
                        startActivity(intent);
                    }
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