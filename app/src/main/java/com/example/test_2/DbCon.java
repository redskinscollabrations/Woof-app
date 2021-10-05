package com.example.test_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCon extends SQLiteOpenHelper {

    public DbCon(Context context) {
        super(context, "woofSchedultbl1.db", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table if not exists schedulet_woof(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, task TEXT, date TEXT)");
        sqLiteDatabase.execSQL("create Table if not exists vaccine_woof(_vid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, vaccineName TEXT, vdate TEXT, sergeonName Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS schedulet_woof");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS vaccine_woof");
    }

    public Boolean taskAdd(String task, String date){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        contentValues.put("date", date);
        long results = MyDB. insert("schedulet_woof", null, contentValues);
        if (results==-1) {
            return false;
        } else{
            return true;
        }

    }

    //read data from the table schedulet_woof
    Cursor readallData(){
        String query = " select * from schedulet_woof ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Boolean taskUpdate(String task, String date, String d_id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        contentValues.put("date", date);
        String whereArgs[] = {d_id.toString()};
        long results = MyDB.update("schedulet_woof", contentValues, "_id=?",whereArgs);
        if (results==-1) {
            return false;
        } else{
            return true;
        }

    }

    public Boolean taskDelete(String d_id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String whereArgs[] = {d_id.toString()};
        long results = MyDB.delete("schedulet_woof", "_id=?",whereArgs);
        if (results==-1) {
            return false;
        } else{
            return true;
        }

    }

    //CRUD functions for Vaccine

    public Boolean vaccineAdd(String vaccine, String vdate){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vaccineName", vaccine);
        contentValues.put("vdate", vdate);
        //contentValues.put("sergeonName", sergeon);
        long results = MyDB. insert("vaccine_woof", null, contentValues);
        if (results == -1) {
            return false;
        } else{
            return true;
        }

    }

    Cursor vaccinereadData(){
        String query = " select * from vaccine_woof ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public Boolean vaccineUpdate(String vaccine, String vdate, String v_id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vaccineName", vaccine);
        contentValues.put("vdate", vdate);
        String whereArgs[] = {v_id.toString()};
        long results = MyDB.update("vaccine_woof", contentValues, "_vid=?",whereArgs);
        if (results==-1) {
            return false;
        } else{
            return true;
        }

    }

    public Boolean vaccineDelete(String v_id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String whereArgs[] = {v_id.toString()};
        long results = MyDB.delete("vaccine_woof", "_vid=?",whereArgs);
        if (results==-1) {
            return false;
        } else{
            return true;
        }

    }
}
