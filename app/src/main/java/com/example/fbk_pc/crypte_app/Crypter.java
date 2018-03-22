package com.example.fbk_pc.crypte_app;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tm161.crypte_app.R;

public class Crypter extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5,btn6, btnTheme;
    Button buton1,buton2,buton3,buton4,button5;
    Sgl db = new Sgl(this);
    SharedPreferences shard;
   ListView listView;

Animation relate,relate1,train_in,train_out;
    boolean openn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shard = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        int mystyle = shard.getInt("mystyle", 0);
        switch (mystyle) {
            case 0:
                this.setTheme(R.style.AppTheme0);
                break;
            case 1:
                this.setTheme(R.style.AppTheme1);
                break;
            case 2:
                this.setTheme(R.style.AppTheme2);
                break;
        }
        setContentView(R.layout.activity_crypter);
        listView=(ListView)findViewById(R.id.liste);
        buton1=(Button)findViewById(R.id.button1);
        buton2=(Button)findViewById(R.id.button2);
        buton3=(Button)findViewById(R.id.button3);
        buton4=(Button)findViewById(R.id.button6);
        button5=(Button)findViewById(R.id.button4);



        relate= AnimationUtils.loadAnimation(this,R.anim.relate);
        train_in= AnimationUtils.loadAnimation(this,R.anim.train_in);
        train_out= AnimationUtils.loadAnimation(this,R.anim.train_out);
        relate1=AnimationUtils.loadAnimation(this,R.anim.relate2);
        listView=(ListView)findViewById(R.id.liste);

        final String[] text={"Coffre Photo","Coffre plus","Coffre Video","Coffre Audio","Envoyer SMS","recevoir SMS"};
        final Integer []iamge={
                R.drawable.abc,
                R.drawable.docicon,
                R.drawable.videoicon,
                R.drawable.mymusic,
                R.drawable.sand,
                R.drawable.recive
        };
        buton1.bringToFront();

        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (openn) {
                    openn=false;
                    buton1.startAnimation(relate);
                    buton2.startAnimation(train_in);
                    buton3.startAnimation(train_in);
                    buton4.startAnimation(train_in);
                    button5.startAnimation(train_in);

                    buton2.setVisibility(View.VISIBLE);
                    buton3.setVisibility(View.VISIBLE);
                    buton4.setVisibility(View.VISIBLE);
                    button5.setVisibility(View.VISIBLE);
                }else{
                    openn=true;
                    buton1.startAnimation(relate1);
                    buton2.startAnimation(train_out);
                    buton3.startAnimation(train_out);
                    buton4.startAnimation(train_out);
                    button5.startAnimation(train_out);

                    buton2.setVisibility(View.INVISIBLE);
                    buton3.setVisibility(View.INVISIBLE);
                    buton4.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);

                }
            }
        });


        listee adpter=new listee(this,text,iamge);
        listView.setAdapter(adpter);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if(position==0){
                    long res = db.GetCountTable();
                    if (res == 0) {
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(Crypter.this);
                        final EditText editText1 = new EditText(Crypter.this);
                        editText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder2.setView(editText1);
                        builder2.setTitle("Identifier le mot de passe");
                        builder2.setMessage("Entrer votre mot de passe de cryptage");

                        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String a = editText1.getText().toString();
                                if (a.length() < 4) {
                                    Toast.makeText(getBaseContext(), "Entrer 4 chiffre", Toast.LENGTH_LONG).show();
                                } else {
                                    boolean reslte = db.inserte(a);
                                    if (reslte == true) {
                                        Toast.makeText(getBaseContext(), "C'est  bon", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Crypter.this, COffphoto.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                            }
                        });
                        builder2.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;

                            }
                        });


                        builder2.show();

                    } else {

                        Intent intent = new Intent(Crypter.this, COffphoto.class);
                        startActivity(intent);
                    }


                }
                if (position==1){
                    long res = db.GetCountTable();
                    if (res == 0) {
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(Crypter.this);
                        final EditText editText1 = new EditText(Crypter.this);
                        editText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder2.setView(editText1);
                        builder2.setTitle("Identifier le mot de passe");
                        builder2.setMessage("Entrer votre mot de passe  de cryptage");
                        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String a = editText1.getText().toString();
                                if (a.length() < 4) {
                                    Toast.makeText(getBaseContext(), "Entrer 4 chiffre", Toast.LENGTH_LONG).show();
                                } else {
                                    boolean reslte = db.inserte(a);
                                    if (reslte == true) {
                                        Toast.makeText(getBaseContext(), "C'est  bon", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Crypter.this, Coffvideo.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                            }
                        });
                        builder2.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;

                            }
                        });
                        builder2.show();
                    } else {

                        Intent intent = new Intent(Crypter.this, Coffvideo.class);
                        startActivity(intent);
                    }

                }
                if (position==2){

                    long res = db.GetCountTable();
                    if (res == 0) {
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(Crypter.this);
                        final EditText editText1 = new EditText(Crypter.this);
                        editText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder2.setView(editText1);
                        builder2.setTitle("Identifier le mot de  passe");
                        builder2.setMessage("Entrer votre mot de passe de cryptage");

                        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String a = editText1.getText().toString();
                                if (a.length() < 4) {
                                    Toast.makeText(getBaseContext(), "Entrer 4 chiffre", Toast.LENGTH_LONG).show();
                                } else {
                                    boolean reslte = db.inserte(a);
                                    if (reslte == true) {
                                        Toast.makeText(getBaseContext(), "C'est  bon", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Crypter.this, Coffmusique.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                            }
                        });
                        builder2.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;

                            }
                        });
                        builder2.show();
                    } else {
                        Intent intent = new Intent(Crypter.this, Coffmusique.class);
                        startActivity(intent);
                    }

                }
                if (position==3){
                    long res = db.GetCountTable();
                    if (res == 0) {
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(Crypter.this);
                        final EditText editText1 = new EditText(Crypter.this);
                        editText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder2.setView(editText1);
                        builder2.setTitle("Identifier le mot de  passe");
                        builder2.setMessage("Entrer votre mot de passe de cryptage");

                        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String a = editText1.getText().toString();
                                if (a.length() < 4) {
                                    Toast.makeText(getBaseContext(), "Entrer 4 chiffre", Toast.LENGTH_LONG).show();
                                } else {
                                    boolean reslte = db.inserte(a);
                                    if (reslte == true) {
                                        Toast.makeText(getBaseContext(), "C'est bon", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Crypter.this, Coffplus.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                            }
                        });
                        builder2.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;

                            }
                        });
                        builder2.show();
                    } else {
                        Intent intent = new Intent(Crypter.this, Coffplus.class);
                        startActivity(intent);
                    }

                }



            }
        });
        final CharSequence[] items = {
                "Theme 1", "Theme 2", "Theme 3"
        };
           button5.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   AlertDialog.Builder builder = new AlertDialog.Builder(Crypter.this);
                   builder.setTitle("Make your selection");
                   builder.setItems(items, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int item) {
                           if (item==0){
                               MyStyle(0);
                           }
                           if (item==1){
                               MyStyle(1);
                           }
                           if (item==2){
                               MyStyle(2);
                           }

                       }
                   });
                   AlertDialog alert = builder.create();
                   alert.show();

               }

           });


















    }


    public boolean onCreateOptionMenu(Menu mune){
       getMenuInflater().inflate(R.menu.menu_crypte,mune);
       return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
         if (id==R.id.theme1){
             Intent intent=new Intent(Crypter.this,COffphoto.class);
             startActivity(intent);
             return true;
         }
         if (id==R.id.theme2){
             return true;
         }
       return true;
   }



    public void MyStyle(int i){
        SharedPreferences.Editor editor=shard.edit();
        editor.putInt("mystyle",i);
        editor.apply();
        finish();
        startActivity(new Intent(this,Crypter.class));

    }
}
