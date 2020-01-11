package com.harshaapps.switchspeaker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import androidx.annotation.MainThread;
import androidx.core.app.NotificationCompat;

public class ReceiveCallService extends Service {
private static final String channelID="com.harshaapps.switchspeaker";
private static final String desc="Notification channel for switch speaker";
private static final int notificationID=22;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent i=new Intent(this, MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this, 0, i, 0);
        NotificationManager nManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel nChannel=new NotificationChannel(channelID, desc, NotificationManager.IMPORTANCE_HIGH);
        nChannel.enableVibration(true);
        NotificationCompat.Builder nBuilder;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            nBuilder=new NotificationCompat.Builder(this, channelID);
        else
            nBuilder=new NotificationCompat.Builder(this);
        nBuilder.setContentTitle("Switch speaker");
        nBuilder.setTicker("Controlling auto speaker of call");
        nBuilder.setContentText("Controlling auto speaker of call");
        nBuilder.setSmallIcon(android.R.drawable.ic_notification_overlay);
        nBuilder.setAutoCancel(true);
nBuilder.setContentIntent(pi);
nManager.createNotificationChannel(nChannel);
        Notification notification=nBuilder.build();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        startForeground(notificationID, notification);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    tm.listen(new ReceivedCallListener(this), PhoneStateListener.LISTEN_CALL_STATE);

        return START_NOT_STICKY;
    }
}
