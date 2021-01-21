package com.hfad.joke;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;

public class DelayedMessageService extends IntentService {

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String EXTRA_MESSAGE = "Mensaje";
    public static final int NOTIFICATION_ID = 5453;


    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v("DelayedMessageService", "Esto es un mensaje");

        synchronized (this){
            try {
                wait(5000);
                System.out.println("1");
            }catch (InterruptedException e){
                e.printStackTrace();
                System.out.println("0");
            }
        }

        String text = intent.getStringExtra(EXTRA_MESSAGE);
        System.out.println("3");
        showText(text);
        System.out.println("final");

    }
    private void showText(final String text){
        Log.v("DelayedMessageService", "Este es el mensaje: " + text);

        System.out.println("4");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.question))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] {0, 1000})
                .setAutoCancel(true);

        System.out.println("5");
        Intent actionLintent = new Intent(this, MainActivity.class);
        System.out.println("6");
        PendingIntent actionPendingIntend = PendingIntent.getActivity(this,0,actionLintent,PendingIntent.FLAG_UPDATE_CURRENT);
        System.out.println("7");
        builder.setContentIntent(actionPendingIntend);
        System.out.println("8");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        System.out.println("9");
        notificationManager.notify(NOTIFICATION_ID, builder.build());
        System.out.println("10");
    }
}