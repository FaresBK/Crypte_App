package com.example.fbk_pc.crypte_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fbk_pc.crypte_app.R;
import com.example.fbk_pc.crypte_app.Sqlite;


public class Beginning extends AppCompatActivity {
    Sqlite db=new Sqlite(this);
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    long res=db.GetCountTable();
                    long res1=db.GetCountTable1();
                     if(res==0 && res1==0) {
                         Intent intent = new Intent(getApplicationContext(), Shema.class);
                         startActivity(intent);finish();}
                     else{
                         Intent intent = new Intent(Beginning.this, Shema2.class);
                         startActivity(intent);
                         finish();
                     }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };
        thread.start();

    }

}
