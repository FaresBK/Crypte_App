package com.example.fbk_pc.crypte_app;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tm161.crypte_app.R;

public class Reception_Sms extends AppCompatActivity {
    Sqlite db=new Sqlite(this);
     TextView textView;
    Button btn1,btn2;
    SharedPreferences shard;
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
        setContentView(R.layout.activity_reception__sms);
        btn1=(Button)findViewById(R.id.ajouter);
        btn2=(Button)findViewById(R.id.decrypter);
        textView=(TextView)findViewById(R.id.textsms);



         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                     textView.setText(db.recivesms());
             }

         });
    }
    public void sms(final Context context,String numbre,String sms){
               db.insertsms(numbre,sms);
    }
}
