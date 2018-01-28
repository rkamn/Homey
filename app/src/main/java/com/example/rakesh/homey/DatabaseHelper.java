package com.example.rakesh.homey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rakesh on 28/1/18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public  static  final String DATABASE_NAME="Registration.db";
    public  static  final String TABLE_NAME="student_table";
    public  static  final String col_1="ID";
    public  static  final String col_2="NAME";
    public  static  final String col_3="EMAIL";
    public  static  final String col_4="PHONE";
    public  static  final String col_5="PASSWORD";
    private DatabaseHelper myDb;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,EMAIL TEXT, PHONE INTEGER,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    //inset data
    public  boolean insertData(String Name,String Email,String Phone,String Password ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,Name);
        contentValues.put(col_3,Email);
        contentValues.put(col_4,Phone);
        contentValues.put(col_5,Password);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return  true;

    }
    public String checkEmail(String Email){
        SQLiteDatabase sqLiteDatabase =myDb.getWritableDatabase();
        String column[] = {DatabaseHelper.col_5};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_NAME,column,DatabaseHelper.col_3
                +" = ' "+ Email +" '", null,null,null,null);
        if (cursor.getCount()<1){
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.col_5));
        cursor.close();
        return password;
    }
}
