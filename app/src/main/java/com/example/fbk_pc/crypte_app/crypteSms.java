package com.example.fbk_pc.crypte_app;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.net.Uri;
import android.provider.ContactsContract;

import se.simbio.encryption.Encryption;

import static android.R.attr.phoneNumber;
import static android.app.Activity.RESULT_OK;

/**
 * Created by TM161 on 21/04/2017.
 */

public class crypteSms {


    public String encryptesms(String a) {

        Encryption encryption = Encryption.getDefault("15xa", "Salt", new byte[16]);
        String encrypted = encryption.encryptOrNull(a);
        return encrypted;
    }

    public String decryptersms(String b) {
        Encryption encryption = Encryption.getDefault("15xa", "Salt", new byte[16]);
        String decrypted = encryption.decryptOrNull(b);
        return decrypted;
    }

    // getNumber(this.getContentResolver());


    public String getNumber(ContentResolver cr) {
        String f=null;
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        // use the cursor to access the contacts
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            // get display name
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            // get phone number
               return  phoneNumber;
        }
        return f;
    }






}
