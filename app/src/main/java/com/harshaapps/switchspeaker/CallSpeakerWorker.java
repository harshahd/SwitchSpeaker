package com.harshaapps.switchspeaker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class CallSpeakerWorker extends Worker implements SensorEventListener {
    private Context mContext;
    private Sensor sensor;
    private SensorManager sensorManager;
private AudioManager am;
    public CallSpeakerWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        mContext = context;
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        am.setMode(AudioManager.MODE_IN_CALL);
        }

    @NonNull
    @Override
    public Result doWork() {
                boolean registered = sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        if (registered)
        {
            Log.d("call", "listener registered.");
            }
        return Result.success();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("call", "Sensor event change");
        if (sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY)
        {
            Log.d("Call", "Getting sensor type");
            if (sensorEvent.values[0]>0)
            {
                Log.d("Call", "Values are true");
                am.setSpeakerphoneOn(true);
            }
            else
            {
                am.setSpeakerphoneOn(false);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



}

