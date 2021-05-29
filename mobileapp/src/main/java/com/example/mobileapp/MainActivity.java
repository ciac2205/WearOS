package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notifyMe(View View){
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        Intent viewIntent = new Intent(this,NotificationDetails.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this,0,viewIntent,PendingIntent.FLAG_UPDATE_CURRENT);



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("ChannelDescription");
            notificationChannel.setDescription("Channel description");

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500,1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                //icon

                .setSmallIcon(R.drawable.ic_launcher_background)
                //title
                .setContentTitle("My notification")
                //content
               .setContentText("Hello from Here Cesar")

                .setContentInfo("info")
                .setContentIntent(viewPendingIntent);

        notificationManager.notify(1,notificationBuilder.build());
    }
    public void voiceInput(View view ){
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        Intent viewIntent = new Intent(this,NotificationDetails.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this,0,viewIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        String reply_label =getResources().getString(R.string.reply_label);
        String[] reply_choices =getResources().getStringArray(R.array.reply_choices);
        RemoteInput remoteInput = new RemoteInput.Builder(NotificationUtils.EXTRA_VOICE_REPLY)
                .setLabel(reply_label)
                .setChoices(reply_choices)
                .build();
        NotificationCompat.Action.Builder notificationActionBuilder =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background, "My voice",viewPendingIntent);
        notificationActionBuilder.addRemoteInput(remoteInput);
        NotificationCompat.Action action = notificationActionBuilder.build();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("ChannelDescription");
            notificationChannel.setDescription("Channel description");

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500,1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                //icon
                .setSmallIcon(R.drawable.ic_launcher_background)
                //title
                .setContentTitle("My notification")
                //content
                .setContentText("Hello from Here Cesar")

                .setContentInfo("info")

                .setContentIntent(viewPendingIntent)
                .addAction(action)
                .extend(new NotificationCompat.WearableExtender().setContentAction(0));

        notificationManager.notify(1,notificationBuilder.build());

    }
}