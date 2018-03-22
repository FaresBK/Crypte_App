package com.example.fbk_pc.crypte_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tm161.crypte_app.R;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

public class Coffplus extends AppCompatActivity {
    com.example.fbk_pc.crypte_app.crypterfile crypterfile =new crypterfile("a");
     Button button;
    Button button1;
    Sgl sgl=new Sgl(this);
    SharedPreferences shard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shard=getSharedPreferences("Setting", Context.MODE_PRIVATE);
        int mystyle=shard.getInt("mystyle",0);
        switch (mystyle){
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
        setContentView(R.layout.activity_coffplus);
        button=(Button)findViewById( R.id.btn1);
        button1=(Button)findViewById(R.id.btn2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogProperties properties = new DialogProperties();
                properties.selection_mode = DialogConfigs.MULTI_MODE;
                properties.selection_type = DialogConfigs.FILE_AND_DIR_SELECT;
                properties.root = new File(DialogConfigs.DEFAULT_DIR);
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = new String[]{"txt","doc","pdf"};

                FilePickerDialog dialog = new FilePickerDialog(Coffplus.this,properties);
                dialog.setTitle("Select +");
                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                    public void onSelectedFilePaths(final String[] files) {
                        final AlertDialog.Builder builder=new AlertDialog.Builder(Coffplus.this);
                        builder.setTitle("Question de choix");
                        builder.setMessage("est ce que vous avez sur de crypter les photos");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                final AlertDialog.Builder builder1 = new AlertDialog.Builder(Coffplus.this);
                                final EditText editText = new EditText(Coffplus.this);
                                editText.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                builder1.setView(editText);
                                builder1.setTitle("confermation");
                                builder1.setMessage("entrer votre motpasse");
                                builder1.setPositiveButton("confermer ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String a = editText.getText().toString();
                                        boolean res = sgl.user(a);
                                        if (res == true) {
                                            crupterphoto crypterphoto = new crupterphoto(files, Coffplus.this, sgl);
                                            crypterphoto.execute();
                                        } else {
                                            Toast.makeText(getBaseContext(), "mot de passe est faux", Toast.LENGTH_LONG).show();
                                        }
                                    }




                                });
                                builder1.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                });

                                builder1.show();


                            }
                        });
                        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });

                        builder.show();

                    }

                });

                dialog.show();

            }






        });


      button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              DialogProperties properties = new DialogProperties();
              properties.selection_mode = DialogConfigs.MULTI_MODE;
              properties.selection_type = DialogConfigs.FILE_AND_DIR_SELECT;
              properties.root = new File(DialogConfigs.DEFAULT_DIR+"/StrBoxx");
              properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
              properties.offset = new File(DialogConfigs.DEFAULT_DIR);
              properties.extensions = new String[]{""};

              FilePickerDialog dialog = new FilePickerDialog(Coffplus.this,properties);
              dialog.setTitle("Select +");
              dialog.setDialogSelectionListener(new DialogSelectionListener() {
                  @Override
                  public void onSelectedFilePaths(final String[] files) {
                      final AlertDialog.Builder builder1 = new AlertDialog.Builder(Coffplus.this);
                      final EditText editText = new EditText(Coffplus.this);
                      editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                      builder1.setView(editText);
                      builder1.setTitle("confermation");
                      builder1.setMessage("entrer votre motpasse");
                      builder1.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              String a = editText.getText().toString();
                              boolean res = sgl.user(a);
                              if (res==true) {
                                  decrypterphoto decrypterphoto = new decrypterphoto(files, Coffplus.this, sgl);
                                  decrypterphoto.execute();
                              }else{
                                  Toast.makeText(getBaseContext()," mot de passe est faux",Toast.LENGTH_LONG).show();
                              }
                          }
                      });
                      builder1.setNegativeButton("non", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              return;
                          }
                      });
                      builder1.show();



                  }
              });
              dialog.show();
          }
      });
    }

  private class crupterphoto extends AsyncTask<String[],Integer,String>{

    String[] filename;
    Context context;
    Sgl sgl;
    ProgressDialog progressDialog;

    public crupterphoto(String[] filename, Context context, Sgl sgl) {
        this.filename = filename;
        this.context = context;
        this.sgl = sgl;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected String doInBackground(String[]... params) {
        String nam;
        for (int i = 0; i < filename.length; i++) {
            File filename1 = new File(filename[i]);
            nam = filename1.getName().toString();

            try {
                if (sgl.inserte1(nam, filename[i])) {
                    crypterfile.EncryptionFile(filename[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setTitle("");
        progressDialog.setMessage("veillez attendre quelque secondes");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }

    protected void onPostExecute(String resulta) {
        progressDialog.dismiss();
    }

  }
  private class decrypterphoto extends AsyncTask<String [],Integer,String> {
     String[] files;
     Context context;
     Sgl sgl;
      ProgressDialog progressDialog1;

     public decrypterphoto(String[] files, Context context, Sgl sgl) {
        this.files = files;
        this.context = context;
        this.sgl = sgl;
        progressDialog1 = new ProgressDialog(context);

     }

    crypterfile crypterfile = new crypterfile("a");

    @Override
    protected String doInBackground(String[]... params) {
        String path = "";
        for (int i = 0; i < files.length; i++) {
            File filenamef = new File(files[i]);
            path = sgl.user1(filenamef.getName().split("\\.ECrypt")[0]);
            try {
                crypterfile.DecryptionFile(files[i], path);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }

        }


        return null;
    }


    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog1.setTitle("");
        progressDialog1.setMessage("veillez attendre quelque secondes");
        progressDialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog1.show();

    }


    protected void onPostExecute(String resulta) {
        progressDialog1.dismiss();
    }


  }


}