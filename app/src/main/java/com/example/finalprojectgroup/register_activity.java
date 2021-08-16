package com.example.finalprojectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class register_activity extends AppCompatActivity {

    Button register;
    EditText name, email, password,repassword;
    ImageView showPassword1, showPassword2;
    CheckBox paidPrem;
    DBHelper DB;
    Boolean showPasswordValue1 = false;
    Boolean showPasswordValue2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        register = findViewById(R.id.Register);
        name = findViewById(R.id.myname);
        email = findViewById(R.id.email);
        showPassword1 = findViewById(R.id.showPassword1);
        showPassword2 = findViewById(R.id.showPassword2);
        password = findViewById(R.id.mypassreg);
        repassword = findViewById(R.id.re_mypass);
        paidPrem = findViewById(R.id.checkBox);
        DB = new DBHelper(this);





        showPassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showPasswordValue1) {
                    showPassword1.setImageResource(R.drawable.img_show_password);
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    showPasswordValue1 = false;
                } else {
                    showPassword1.setImageResource(R.drawable.img_hide_password);
                    password.setTransformationMethod(null);
                    showPasswordValue1 = true;
                }

            }
        });

        showPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showPasswordValue2) {
                    showPassword2.setImageResource(R.drawable.img_show_password);
                    repassword.setTransformationMethod(new PasswordTransformationMethod());
                    showPasswordValue2 = false;
                } else {
                    showPassword2.setImageResource(R.drawable.img_hide_password);
                    repassword.setTransformationMethod(null);
                    showPasswordValue2 = true;
                }

            }
        });



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

                Cursor cursor = DB.getdata(myEmail);
                String premium;

                if(pass1.equals(pass2) && pass1 != ""){
                    if(cursor.getCount() > 0){
                        while (cursor.moveToNext()) {
                            Log.d("Login", "onClick: " + cursor.getString(0) + " "
                                    + cursor.getString(1) + " "
                                    + cursor.getString(2) + " "
                                    + cursor.getString(3) + " ");
                        }
                    }else{
                        if(isCheckedbox){
                            premium = "true";
                        }else{
                            premium = "false";
                        }
                        //Toast.makeText(register_activity.this, "Creating your profile", Toast.LENGTH_SHORT).show();
                        DB.insertuserdata(myEmail,myName,pass1,premium);
                        Intent intent = new Intent(register_activity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}