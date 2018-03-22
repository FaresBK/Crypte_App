package com.example.fbk_pc.crypte_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.example.fbk_pc.crypte_app.NewMessageNotification;
import com.example.fbk_pc.crypte_app.Reception_Sms;
import com.example.fbk_pc.crypte_app.crypteSms;

/**
 * Created by TM161 on 21/04/2017.
 */
public class ReciveMessage extends BroadcastReceiver {
    crypteSms cryptesms=new crypteSms();
    static int id=1;
    SmsManager smsManager=SmsManager.getDefault();
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle=intent.getExtras();
        try {
            if(myBundle!=null){
                final Object [] Messagecontent=(Object[])myBundle.get("pdus");
                for (int i=0;i<Messagecontent.length;i++){
                    SmsMessage mynewSms=SmsMessage.createFromPdu((byte[])Messagecontent[i]);
                    NewMessageNotification nome=new NewMessageNotification();
                    nome.notify(context,mynewSms.getDisplayOriginatingAddress(),mynewSms.getDisplayMessageBody(),id);
                    id++;
                     Reception_Sms reception_sms=new Reception_Sms();
                      reception_sms.sms(context,mynewSms.getDisplayOriginatingAddress(),mynewSms.getDisplayMessageBody());

                }

            }


        }catch (Exception ex){

        }
    }
}
