package com.example.fbk_pc.crypte_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by TM161 on 13/03/2017.
 */

public class Sgl extends SQLiteOpenHelper{
    public static final String BDfares="fares.db";
    public Sgl(Context context) {
        super(context, BDfares, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table yazide (id INTEGER PRIMARY KEY AUTOINCREMENT , passe TEXT)");
        db.execSQL("create table table1 (id INTEGER PRIMARY KEY AUTOINCREMENT ,name TEXT, path TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             db.execSQL("DROP TABLE IF EXISTS yazide");
            onCreate(db);
    }

    public boolean inserte (String editText1){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
            contentValues.put("passe",editText1);
            long res=db.insert("yazide",null,contentValues);
             if (res==-1)
                 return  false;
                else
                 return true;


    }
    public boolean inserte1(String Nam,String Path){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",Nam);
        contentValues.put("path",Path);
        Long res1=db.insert("table1",null,contentValues);
        if (res1==-1)
            return false;
        else
            return true;

    }
    public String user1(String Name){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from table1 ",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            if (Name.equals(cursor.getString(1))){
                return cursor.getString(2);

            }
            cursor.moveToNext();
        }
        return Environment.getDownloadCacheDirectory().toString()+"/"+Name ;
    }

    public boolean user(String edite) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from yazide", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            if (edite.equals(res.getString(1)))
                return true;
            res.moveToNext();
        }
        return false;
    }
    public long GetCountTable(){
        SQLiteDatabase database = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(database, "yazide");
    }



}
