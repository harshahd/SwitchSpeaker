package com.harshaapps.switchspeaker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class MainActivity extends AppCompatActivity {
private static final String[] permissions={Manifest.permission.MODIFY_AUDIO_SETTINGS,Manifest.permission.READ_PHONE_STATE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(permissions[0])!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(permissions,0);
        }
    IntentFilter inf=new IntentFilter();
        inf.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        inf.addAction("android.intent.action.PHONE_STATE");
                                        registerReceiver(new CallSpeakerReceiver(), inf);

    }

    }

