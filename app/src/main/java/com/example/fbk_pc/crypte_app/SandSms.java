package com.example.fbk_pc.crypte_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tm161.crypte_app.R;

public class SandSms extends AppCompatActivity {
    EditText editText, editText1;
    Button button, button1,button2;

    SharedPreferences shard;
    ProgressDialog progressDialog;
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
        setContentView(R.layout.activity_sand_sms);
        editText = (EditText) findViewById(R.id.sendto);
        editText1 = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.sand);
        button1 = (Button) findViewById(R.id.ajouter);
        button2=(Button)findViewById(R.id.crypter);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(Uri.parse("content://contacts/people/"));
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);


            }
        });

         button2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 progressDialog=new ProgressDialog(SandSms.this);
                 progressDialog.setTitle("Wait");
                 progressDialog.setMessage("Votre message est ecrypter");
                 progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                  final cruptersms cruptersms=new cruptersms();
                 cruptersms.execute(editText1.getText().toString());

                 // String cryptersms = crypteSms.encryptesms(editText1.getText().toString());
                 //editText1.setText(cryptersms);
             }
         });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PhoneNumbre = editText.getText().toString();
                String Sms=editText1.getText().toString();
                if (PhoneNumbre.equals("")){
                      Toast.makeText(getBaseContext(),"Ajouter un numero",Toast.LENGTH_LONG).show();
                }else {

                    if (Sms.equals("")) {
                        Toast.makeText(getBaseContext(), "ecrire un message", Toast.LENGTH_LONG).show();
                    } else {
                        SmsManager smsMessage = SmsManager.getDefault();
                        smsMessage.sendTextMessage(PhoneNumbre, null, Sms, null, null);
                        Toast.makeText(getBaseContext(), "votre message est envoiyer", Toast.LENGTH_LONG).show();
                    }
                }



            }

        });
    }

    static final int PICK_CONTACT_REQUEST = 0;
    protected void onActivityResult(int requestCode, int resultcode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultcode == RESULT_OK) {
                Uri contectUri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                Cursor cursor = getContentResolver().query(contectUri, projection, null, null, null);
                cursor.moveToFirst();
                int colemnt = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String numbre = cursor.getString(colemnt);
                editText.setText(numbre);

            }

        }

    }
    private class cruptersms extends AsyncTask<String,Integer,String>{

        com.example.fbk_pc.crypte_app.crypteSms crypteSms = new crypteSms();
            String cryptersms;
        @Override
        protected String doInBackground(String...params) {
             cryptersms=crypteSms.encryptesms(params[0]);
            return null;

        }

        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog.show();

        }
        protected  void onProgressUpdate(Integer ... params){

        }

        protected  void onPostExecute(String resulta){
               editText1.setText(cryptersms);
            progressDialog.dismiss();
        }

    }

}