package com.example.woof_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

    /* Creating the database */
    public class MyDatabaseHelper extends SQLiteOpenHelper {
        private Context context;
        private static final String DATABASE_NAME = "woof.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_NAME = "Supplier_menu";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_MENU = "Menu";
        private static final String COLUMN_AMOUNT = "Amount";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_MENU + " TEXT, " +
                        COLUMN_AMOUNT + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /* Insert Method implementation */
    void addmenu(String Menu, int Amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MENU, Menu);
        cv.put(COLUMN_AMOUNT, Amount);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    /* To return the cursor object */
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /* Update Method implementation */
    public Boolean updateData(String row_id, String Menu, String Amount){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MENU, Menu);
        cv.put(COLUMN_AMOUNT, Amount);

        long result = db.update(TABLE_NAME, cv,"_id=?", new String[]{row_id});
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }

    /* Delete Method implementation */
    public Boolean deleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME,"_id=?", new String[]{row_id});
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }
}
