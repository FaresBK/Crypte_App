package com.example.fbk_pc.crypte_app;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tm161.crypte_app.R;
import com.takwolf.android.lock9.Lock9View;

public class Shema3 extends AppCompatActivity {
    Sqlite db = new Sqlite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shema3);


        Lock9View lock9View = (Lock9View) findViewById(R.id.lock_9_view);
        lock9View.setCallBack(new Lock9View.CallBack() {
            @Override
            public void onFinish(String password) {
                String s = password;
                db.Updateshema(s);
                Intent intent = new Intent(Shema3.this, Shema2.class);
                startActivity(intent);
                finish();

            }

        });


    }

}




