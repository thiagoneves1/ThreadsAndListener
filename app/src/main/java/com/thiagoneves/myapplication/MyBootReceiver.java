package com.thiagoneves.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBootReceiver extends BroadcastReceiver {
    private static final String TAG = "#THREAD MyStartServiceReceiver";

    private static final int FIRST_TIME_AFTER_BOOT = 1;
    @Override
    public void onReceive(Context context, Intent intent) { //reschedule when device reboot
        Log.i(TAG, "onReceive: boot completed, need reschedule Job ");
        Util.scheduleJob(context, FIRST_TIME_AFTER_BOOT);
    }
}