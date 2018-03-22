package com.example.fbk_pc.crypte_app;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tm161.crypte_app.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.takwolf.android.lock9.Lock9View;

import java.io.File;

public class Shema2 extends AppCompatActivity {
    Sqlite db = new Sqlite(this);
     TextView textView;
    Animation anim;
    Button button,button1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shema2);
        button = (Button) findViewById(R.id.btn1);
        textView= (TextView)findViewById(R.id.question1);

        Lock9View lock9View = (Lock9View) findViewById(R.id.lock_9_view);
        lock9View.setCallBack(new Lock9View.CallBack() {
            @Override
            public void onFinish(String password) {


                String s = password;
                if (db.user1(s) == true) {
                   File res = new File(Environment.getExternalStorageDirectory() + "/StrBoxx");
                    if (!res.exists()) {
                        if (res.mkdir()) {
                            Toast.makeText(Shema2.this, "Directory Created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Shema2.this, "Directory error", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        if(password.length()>=4){
                            Intent intent = new Intent(Shema2.this, Crypter.class);
                            startActivity(intent);
                        }else{
                                Toast.makeText(getBaseContext(),"chema tréé chorte",Toast.LENGTH_LONG).show();
                        }

                    }

                } else {
                    Toast.makeText(getBaseContext(), "le Shéma est faux", Toast.LENGTH_LONG).show();
                }


            }
       });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long res = db.GetCountTable();
                if (res == 0) {
                    Intent intent = new Intent(Shema2.this, Inscription.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Shema2.this, Login.class);
                    startActivity(intent);


                }


            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(Shema2.this);
                        builder1.setTitle("Question sécurité");
                        final EditText editText = new EditText(Shema2.this);
                        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder1.setView(editText);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String f = editText.getText().toString();
                                boolean res = db.Question1(f);
                                if (res == true) {
                                    Intent intent = new Intent(Shema2.this, Shema3.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(getBaseContext(), "Votre Question est faux", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                        builder1.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });

                 builder1.show();
                    }

        });




    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
