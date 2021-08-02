package com.example.finalprojectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class register_activity extends AppCompatActivity {

    Button register;
    EditText name, email, password,repassword;
    CheckBox paidPrem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        register = findViewById(R.id.Register);
        name = findViewById(R.id.myname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.mypassreg);
        repassword = findViewById(R.id.re_mypass);
        paidPrem = findViewById(R.id.checkBox);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("password", password.getText() + " is equal to  " + repassword.getText());
                String pass1 = String.valueOf(password.getText());
                String pass2 = String.valueOf(repassword.getText());
                String myName = String.valueOf(name.getText());
                String myEmail = String.valueOf(email.getText());
                boolean isCheckedbox = paidPrem.isChecked();
                Log.d("isCheckedbox", "onClick: " + isCheckedbox);
                if(pass1.equals(pass2) && pass1 != ""){
                    Intent intent = new Intent(register_activity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}