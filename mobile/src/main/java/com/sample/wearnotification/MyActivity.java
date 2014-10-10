package com.sample.wearnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    public void createWearNotification(View view){

        int notificationId = 001;
//        String eventDescription = "Notificação";
        // Create an intent for the reply action
        Intent actionIntent = new Intent(this, ActivityResult1.class);
        PendingIntent actionPendingIntent =
                PendingIntent.getActivity(this, 0, actionIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        Intent actionIntent2 = new Intent(this, ActivityResult2.class);
        PendingIntent actionPendingIntent2 =
                PendingIntent.getActivity(this, 0, actionIntent2,
                        0);
        // Create the action
        NotificationCompat.Action action1 =
                new NotificationCompat.Action.Builder(R.drawable.ic_plusone_medium_off_client,getString(R.string.action1), actionPendingIntent).build();
        NotificationCompat.Action action2 =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher,getString(R.string.action2), actionPendingIntent2).build();

        // Specify the 'big view' content to display the long
        // event description that may not fit the normal content text.
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText(getString(R.string.notification));

        // Build the notification and add the action via WearableExtender
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(getString(R.string.title))
                         .setAutoCancel(true)
                        .setOngoing(false)
                        .setAutoCancel(true)
                        .addAction(action1)
                        .addAction(action2)
                        //.extend(new NotificationCompat.WearableExtender().addAction(action2))
                        .setStyle(bigStyle)
                        .build();

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Issue the notification with notification manager.
        notificationManager.notify(notificationId, notification);

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender(notification);
//        boolean hintHideIcon = wearableExtender.getHintHideIcon();
    }
}
