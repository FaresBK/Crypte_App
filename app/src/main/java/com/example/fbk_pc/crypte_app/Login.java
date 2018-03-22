package com.example.fbk_pc.crypte_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tm161.crypte_app.R;

import java.io.File;

public class Login extends AppCompatActivity {
    Button button1;
    EditText editText1,editText2;
    Button button;
    Sqlite db=new Sqlite(this);
    TextView textView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            button1=(Button)findViewById(R.id.bt1);
            editText1=(EditText)findViewById(R.id.user2);
            editText2=(EditText)findViewById(R.id.pass2);
            button=(Button)findViewById(R.id.bt2);
            textView=(TextView)findViewById(R.id.verifier);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a=editText1.getText().toString();
                    String b=editText2.getText().toString();

                    if (db.user(a)==true) {
                        if (db.pass(b)==true){
                            File res=new File(Environment.getExternalStorageDirectory()+"/StrBoxx");
                            if (!res.exists()) {
                                if (res.mkdir()) {
                                    Toast.makeText(Login.this, "Directory Created", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Login.this, "Directory error", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Intent intent = new Intent(Login.this, Crypter.class);
                                startActivity(intent);
                                finish();
                            }

                        }else{
                            Toast.makeText(getBaseContext(),"password est faux",Toast.LENGTH_LONG).show();}
                    }else{
                        Toast.makeText(getBaseContext(),"user name est faux",Toast.LENGTH_LONG).show();
                    }

                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long res=db.GetCountTable1();
                    if (res==0){
                        Intent intent=new Intent(Login.this,Shema.class);
                        startActivity(intent);
                        finish();

                    }else{
                        Intent intent=new Intent(Login.this,Shema2.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
                    builder.setTitle("Question sécurité");
                    final EditText editText = new EditText(Login.this);
                    editText.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    builder.setView(editText);
                    builder.setPositiveButton("ok",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String s=editText.getText().toString();
                            boolean h=db.Question1(s);
                            if(h == true){
                                final AlertDialog.Builder builder1=new AlertDialog.Builder(Login.this);
                                builder1.setTitle("entrey nouvelle mot passe");
                                final EditText editText1 = new EditText(Login.this);
                                editText1.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                builder1.setView(editText1);
                                builder1.setPositiveButton("confermer",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String s1=editText1.getText().toString();
                                        db.Updatepasseword(s1);
                                    }
                                });
                                builder1.setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                });
                                builder1.show();

                            }else {
                                Toast.makeText(getBaseContext(),"mazale", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    builder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();
                }
            });

        }
     @Override
     public void  onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
     }
    }

