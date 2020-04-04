package com.thiagoneves.myapplication;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.PersistableBundle;
import android.util.Log;

import static com.thiagoneves.myapplication.Util.KEY_TIME;
import static com.thiagoneves.myapplication.Util.MAX_EXECUTION;

public class MyJobService extends JobService {
    private static final String TAG = "#THREAD SyncService";

    @Override
    public boolean onStartJob(JobParameters params) {

        final PersistableBundle extras = params.getExtras();
        final int time = extras.getInt(KEY_TIME, MAX_EXECUTION); //get default or not ?

        Log.i(TAG, "Executing short background task or create another thread, service, for long execution. This time is " + time);

        if (time < MAX_EXECUTION) { //need executed 10 times
            int nextTime = time + 1;
            Util.scheduleJob(getApplicationContext(), nextTime); // reschedule the job
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}