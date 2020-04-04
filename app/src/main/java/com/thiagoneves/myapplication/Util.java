package com.thiagoneves.myapplication;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;

public class Util {

    private static final String TAG = "#THREAD Util";
    public static final String KEY_TIME = "time";
    public static final int MAX_EXECUTION = 10;

    // schedule the start of the service every 10 - 30 seconds
    public static void scheduleJob(Context context, int time) {
        Log.i(TAG, "scheduleJob: ");

        PersistableBundle bundle = new PersistableBundle();
        bundle.putInt(KEY_TIME, time);

        ComponentName serviceComponent = new ComponentName(context, MyJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(1000); // wait at least
        builder.setOverrideDeadline(3 * 1000); // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network, for this example don't need this
        //builder.setRequiresDeviceIdle(true); // device should be idle, for this example don't need this
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not, for this example don't need this
        builder.setExtras(bundle); //add extras the times to control, executed 10 times after boot, it's only example
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }
}
