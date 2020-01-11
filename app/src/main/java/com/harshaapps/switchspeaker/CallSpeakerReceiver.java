package com.harshaapps.switchspeaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class CallSpeakerReceiver extends BroadcastReceiver {
    private static final String state="android.intent.action.PHONE_STATE";
    @Override
    public void onReceive(Context context, Intent intent) {
 Intent i=new Intent(context, ReceiveCallService.class);
if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
context.startForegroundService(i);
else
    context.startService(i);
            Log.d("SwitchSpeaker", intent.getAction());

            }
}

