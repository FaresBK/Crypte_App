package com.example.fbk_pc.crypte_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * déveloper par fares blekacem
 * Created by TM161 on 21/02/2017.
 * */

public class Sqlite extends SQLiteOpenHelper {
    public static final String BDsql = "data.db";

    public Sqlite(Context context) {
        super(context, BDsql, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mytable (id INTEGER PRIMARY KEY AUTOINCREMENT , nom TEXT,password TEXT, Confpasswerd TEXT, email TEXT )");
        db.execSQL("create table mytable1 (id INTEGER PRIMARY KEY AUTOINCREMENT,t1 TEXT)");
        db.execSQL("create table mytable2 (id INTEGER PRIMARY KEY AUTOINCREMENT,t2 TEXT)");
        db.execSQL("create table mytable3(id INTEGER PRIMARY KEY AUTOINCREMENT,numbre TEXT,sms TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS mytable");
       // onCreate(db);
    }

    public boolean insertedata(String editText1, String editText2, String editText3, String editText4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nom", editText1);
        contentValues.put("password", editText2);
        contentValues.put("Confpasswerd", editText3);
        contentValues.put("email", editText4);
        long reslta = db.insert("mytable", null, contentValues);
        if (reslta == -1)
            return false;
        else
            return true;
    }

    public boolean inserte1(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("t1",s);
        long reslta = db.insert("mytable1", null, contentValues);
        if (reslta==-1)
            return false;
        else
            return true;
    }
    public boolean inserte2(String f){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("t2",f);
        Long reslta =db.insert("mytable2",null,contentValues);
        if (reslta==-1)
            return  false;
        else
            return true;
    }

    public boolean insertsms(String numbre,String sms){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("numbre",numbre);
        contentValues.put("sms",sms);
        long reslta=db.insert("mytable3",null,contentValues);
                if(reslta==-1)
                    return false;
                else
                    return true;
    }


    public ArrayList getarray() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mytable", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            String a0 = res.getString(1);
            String a2 = res.getString(3);


            arrayList.add(a0 + " - + - " + a2);
            res.moveToNext();
        }

        return arrayList;
    }

    //public boolean getuser(String editText1, String editText2) {

    //SQLiteDatabase db = this.getReadableDatabase();
    //Cursor res = db.rawQuery("SELECT nom,password FROM mytable WHERE nom ='" + editText1 + "' AND password ='" + editText2 + "'", null);
    //if (res.equals(-1))
    //return false;
    //else
    //return true;

    //}
    public boolean user1(String s) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mytable1", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            if(s.equals(res.getString(1)))
                return true;


            res.moveToNext();
        }
        return false;
    }

    public boolean user(String a) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mytable", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            if(a.equals(res.getString(1)))
                return true;

            res.moveToNext();
        }
        return false;
    }
     public boolean Question(String s){
         SQLiteDatabase db=this.getReadableDatabase();
         Cursor res2=db.rawQuery("select * from mytable",null);
         res2.moveToFirst();
         while(res2.isAfterLast()==false){
             if (s.equals(res2.getString(5)))
                 return true;

            res2.moveToNext();
         }
         return false;
     }
    public boolean Question1(String f){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res2=db.rawQuery("select * from mytable2",null);
        res2.moveToFirst();
        while(res2.isAfterLast()==false){
            if (f.equals(res2.getString(1)))
                return true;
            res2.moveToNext();
        }
        return false;
    }

    public String recivesms(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from mytable3",null);
         return res.getString(2);
    }


    public void Updateshema(String s){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update mytable1 set t1='"+s+"'where id=1");

    }
    public void Updatepasseword(String s1){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update mytable set password='"+s1+"'where id=1");
        db.execSQL("update mytable set Confpasswerd='"+s1+"'where id=1");

    }


    public boolean pass(String edite) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mytable", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            if(edite.equals(res.getString(3)))
                return true;

            res.moveToNext();
        }
        return false;
    }
    public long GetCountTable(){
        SQLiteDatabase database = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(database, "mytable");

    }
    public long GetCountTable1(){
        SQLiteDatabase database = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(database, "mytable1");
    }
    public long GetCountTable2(){
        SQLiteDatabase database = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(database, "mytable2");
    }
}
