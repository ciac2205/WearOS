package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;


public class NotificationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);

        CharSequence charSequence =getMessageText(getIntent());
       // String reply_was = getResources().getString(R.string.reply_was);
       // Toast.makeText(this,reply_was+ charSequence, Toast.LENGTH_LONG).show();
        String result = charSequence.toString();


        if(result.equalsIgnoreCase("yes")){
//open map
            Intent mapIntent= new Intent(Intent.ACTION_VIEW);
            mapIntent.setData(Uri.parse("geo:407588, -73.985131"));
            startActivity(mapIntent);


        }
        else if (result.equalsIgnoreCase("no")) {
            //send SMS
           /* if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)) {

                }else {
           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
           */
            try {
                String phone = "2384010776";
                String sms = "sms Message";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone,
                        null, sms, null, null);
                Toast.makeText(this, "Message is Sent", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to send Message", Toast.LENGTH_SHORT).show();

            }
        }
        else if (result.equalsIgnoreCase("may be")){
// show toast
            Toast.makeText(this, "still deciding",Toast.LENGTH_SHORT).show();

        }
    }

    private CharSequence getMessageText(Intent intent){
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if(remoteInput != null){
            return remoteInput.getCharSequence((NotificationUtils.EXTRA_VOICE_REPLY));
        }
        return null;
    }
}