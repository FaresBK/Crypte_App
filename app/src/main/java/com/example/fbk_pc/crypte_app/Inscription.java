package com.example.fbk_pc.crypte_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tm161.crypte_app.R;

public class Inscription extends AppCompatActivity {
Button button1,button2;
    String a,b,c,d,f;
    EditText editText1,editText2,editText3,editText4;
    Sqlite db=new Sqlite(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        button1 = (Button) findViewById(R.id.bt1);
        button2 = (Button) findViewById(R.id.bt2);
        editText1 = (EditText) findViewById(R.id.user);
        editText2 = (EditText) findViewById(R.id.pass);
        editText3 = (EditText) findViewById(R.id.Conf);
        editText4 = (EditText) findViewById(R.id.email);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = editText1.getText().toString();
                b = editText2.getText().toString();
                c = editText3.getText().toString();
                d = editText4.getText().toString();

                if (a.equals(""))
                    Toast.makeText(getBaseContext(), "le champ de nom est vide", Toast.LENGTH_LONG).show();
                if (b.equals(""))
                    Toast.makeText(getBaseContext(), "le champ de prenom est vide", Toast.LENGTH_LONG).show();
                if (c.equals(""))
                    Toast.makeText(getBaseContext(), "le champ de password est vide", Toast.LENGTH_LONG).show();
                if (d.equals(""))
                    Toast.makeText(getBaseContext(), "le champ email est vide", Toast.LENGTH_LONG).show();


                else if (a.length() > 4) {
                    if (b.equals(c)) {
                        if (d.contains("@")) {

                                boolean reslte = db.insertedata(a, b, c, d);
                                if (reslte == true) {
                                    long res2 = db.GetCountTable2();
                                    if (res2 == 0) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Inscription.this);
                                        builder.setTitle("Question de Sécurité");
                                        final EditText editText = new EditText(Inscription.this);
                                        builder.setView(editText);
                                        builder.setPositiveButton("enrgistrer", new DialogInterface.OnClickListener()

                                                {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        String qeustion = editText.getText().toString();
                                                        boolean res = db.inserte2(qeustion);
                                                        if (res == true) {
                                                            Toast.makeText(getBaseContext(), "ci bon", Toast.LENGTH_LONG);
                                                        } else {
                                                            Toast.makeText(getBaseContext(), "la Question est faux", Toast.LENGTH_LONG);
                                                        }

                                                    }
                                                }

                                        );
                                        builder.show();
                                    }else {
                                        Toast.makeText(getBaseContext(), "ci bon", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Inscription.this, Shema2.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }


                        } else {
                            Toast.makeText(getBaseContext(), "le E-mail et faux", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getBaseContext(), "le passwred incorect", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "le User Name et trée petit", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
