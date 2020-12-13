package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static final int Db_verzio = 1;
    public static final String Db_nev = "Android.db";

    public DataBase(Context context) {
        super(context, Db_nev, null, Db_verzio);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE IF NOT EXISTS felhasznalo (id INTEGER PRIMARY KEY AUTOINCREMENT, email VARCHAR(100) NOT NULL UNIQUE, felhnev VARCHAR(100) NOT NULL UNIQUE, jelszo VARCHAR(255) NOT NULL, teljesnev INTEGER NOT NULL" + ")";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL = "DROP TABLE IF EXISTS felhasznalo";
        db.execSQL(SQL);
        onCreate(db);
    }

    public Cursor login(String felhnev, String jelszo) {
        SQLiteDatabase db = this.getReadableDatabase();
        return this.getReadableDatabase().rawQuery("SELECT teljesnev FROM felhasznalo WHERE felhnev = '" + felhnev + "' and jelszo = '" + jelszo + "'" ,null );
    }

    public boolean register(String email, String felhnev, String jelszo, String teljnev)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("felhnev", felhnev);
        values.put("jelszo", jelszo);
        values.put("teljesnev", teljnev);
        long result = db.insert("felhasznalo", null, values);

        return result != -1;
    }

}
