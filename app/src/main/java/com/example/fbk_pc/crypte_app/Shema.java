package com.example.fbk_pc.crypte_app;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.tm161.crypte_app.R;
import com.takwolf.android.lock9.Lock9View;

public class Shema extends AppCompatActivity {
    Sqlite db=new Sqlite(this);

    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shema);
        Lock9View lock9View = (Lock9View) findViewById(R.id.lock_9_view);
        lock9View.setCallBack(new Lock9View.CallBack() {
            @Override
            public void onFinish(String password) {

                long res=db.GetCountTable1();
                if (res==0) {
                   if (password.length()>=4) {
                       String s = password;
                       boolean reslte = db.inserte1(s);
                       if (reslte == true) {
                           Intent intent = new Intent(getApplicationContext(), Inscription.class);
                           startActivity(intent);

                           Toast.makeText(getBaseContext(), "ci bon ", Toast.LENGTH_LONG).show();
                           finish();
                       } else {
                           Toast.makeText(getBaseContext(), "faux", Toast.LENGTH_LONG).show();
                       }
                   }else{
                       Toast.makeText(getBaseContext(),"votre Shema est tréé chorte",Toast.LENGTH_LONG).show();
                   }
                }else{
                    Intent intent = new Intent(getApplicationContext(), Shema2.class);
                    startActivity(intent);
                }
            }

        });
    }
}
