package com.example.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Historydata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Historydetails(calc TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Historydetails");
    }

    public Boolean inserthistorydata(String calc){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("calc", calc);
        long result = DB.insert("Historydetails", null, contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Historydetails LIMIT 50", null);
        return cursor;
    }

    public void deleteData(){
        SQLiteDatabase DB = this.getWritableDatabase();

        DB.execSQL("DELETE FROM Historydetails");
    }

}
