package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "student.sqLiteDatabase";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";
    public static final String COL_5 = "ADDRESS";
    public static final String COL_6 = "DEPARTMENT";
    public static final String COL_7 = "AGE";



    public DatabaseHelper( Context context) {
        super(context,DATABASE_NAME,null , 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME TEXT, SURNAME TEXT, MARKS INTEGER, ADDRESS  TEXT,DEPARTMENT TEXT, AGE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(new StringBuilder().append("DROP TABLE IF EXIST").append(TABLE_NAME).toString());

        onCreate(sqLiteDatabase);
    }

    public boolean insertData( String name, String surname, String marks ,String address, String department,String age)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        contentValues.put(COL_5,address);
        contentValues.put(COL_6,department);
        contentValues.put(COL_7,age);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME,null);
        return res;

    }

}
