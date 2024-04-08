package com.example.loginsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context)
    {

        super(context, "Signup.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int i, int j) {

        mydb.execSQL("drop Table if exists alredy");

    }
    public Boolean insertData(String email, String password){
        SQLiteDatabase mydb=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = mydb.insert("allusers", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean emailcheck(String email){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from allusers where email = ?", new String[]{email});
        if(cursor.getCount() > 0){
            return true;
        }
        else
            return  false;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select  * from allusers where email= ? and password = ?", new String[]{email, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
