package com.sample.wearnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createNotification(View view) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .addAction(R.drawable.ic_launcher, "Call", pIntent)
                .addAction(R.drawable.ic_launcher, "More", pIntent)
                .addAction(R.drawable.ic_launcher, "And more", pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= NotificationCompat.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }

    public void createWearNotification(View view){

        int notificationId = 001;
        String eventDescription = "desc";
        // Create an intent for the reply action
        Intent actionIntent = new Intent(this, NotificationReceiverActivity.class);
        PendingIntent actionPendingIntent =
                PendingIntent.getActivity(this, 0, actionIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher,getString(R.string.label), actionPendingIntent).build();

        // Specify the 'big view' content to display the long
        // event description that may not fit the normal content text.
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText(eventDescription);

        // Build the notification and add the action via WearableExtender
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(getString(R.string.title))
                        .setContentText(getString(R.string.content))
                        .extend(new WearableExtender().addAction(action))
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
