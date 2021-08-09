package com.example.finalprojectgroup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table UserLogindetails(email Text primary key, name Text, password Text, premium Text)");
        db.execSQL("create Table UserMealdetails(email Text, day Text, breakfast Text, lunch Text, snacks Text, dinner Text)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop Table if exists UserLogindetails");
        db.execSQL("drop Table if exists UserMealdetails");

    }

    public Boolean insertuserdata(String email, String name, String password, String premium){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("email",email);
        contentValue.put("name", name);
        contentValue.put("password", password);
        contentValue.put("premium", premium);
        long result = DB.insert("UserLogindetails",null,contentValue);
        return result != -1;
    }


    public Boolean deleteuserdata(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserLogindetails where name = ?", new String[]{email});
        if(cursor.getCount()>0){
            long result = DB.delete("UserLogindetails", "email=?", new String[]{email});
            return result != -1;
        }
        return false;
    }

    public Cursor getdata(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserLogindetails where email = ?", new String[]{email});
        //Cursor cursor = DB.rawQuery("Select * from UserLogindetails", null) ;
        return cursor;
    }


    public Boolean insertuserMealdata(String email, String day, String breakfast, String lunch, String snacks, String dinner){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues(); // keep things in pair
        // (Coloum, value)
        // email Text, day Text, breakfast Text, lunch Text, snacks Text, dinner Text
        contentValue.put("email",email);
        contentValue.put("day",day);
        contentValue.put("breakfast", breakfast);
        contentValue.put("lunch", lunch);
        contentValue.put("snacks", snacks);
        contentValue.put("dinner", dinner);
        long result = DB.insert("UserMealdetails",null,contentValue);

        return result != -1;

    }


    public Boolean updateuserMealdata(String email, String day, String breakfast, String lunch, String snacks, String dinner){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues(); // keep things in pair
        contentValue.put("breakfast", breakfast);
        contentValue.put("lunch", lunch);
        contentValue.put("snacks", snacks);
        contentValue.put("dinner", dinner);
        Cursor cursor = DB.rawQuery("Select * from UserMealdetails where email = ? and day = ?", new String[]{email,day});

        if(cursor.getCount()>0){
            long result = DB.update("UserMealdetails",contentValue, "email = ? and day = ?", new String[]{email, day});
            return result != -1;
        }
        return false;
    }

    public Cursor getMealdata(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserMealdetails where email = ?", new String[]{email});
        //Cursor cursor = DB.rawQuery("Select * from UserLogindetails", null) ;
        return cursor;
    }

    public Boolean deleteuserMealdata(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserLogindetails where email = ?", new String[]{email});
        if(cursor.getCount()>0){
            long result = DB.delete("UserMealdetails", "email=?", new String[]{email});
            return result != -1;
        }
        return false;
    }

}
