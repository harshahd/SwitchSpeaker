package com.harshaapps.switchspeaker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class ReceivedCallListener extends PhoneStateListener {
    private Context mContext;
        ReceivedCallListener(Context context)
    {
        super();
        mContext=context;
    }
    @Override
    public void onCallStateChanged(int state, String phoneNumber) {
if (state== TelephonyManager.CALL_STATE_IDLE)
{

}
else if (state==TelephonyManager.CALL_STATE_OFFHOOK)
{
    OneTimeWorkRequest.Builder otrb=new OneTimeWorkRequest.Builder(CallSpeakerWorker.class);
    WorkManager.getInstance().enqueue(otrb.build());
}
    }

}
