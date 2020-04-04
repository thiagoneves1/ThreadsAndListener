package com.thiagoneves.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SomeTask extends AsyncTask<Void, Void, String> {

    private WeakReference<OnEventListener<String>> mCallBack;
    private Exception mException;


    public SomeTask(OnEventListener callback) {
        mCallBack = new WeakReference<OnEventListener<String>>(callback);
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            Random rand = new Random();

            int randomNum = rand.nextInt(100);
            Thread.sleep(1000);
                return "HELLO Random number is " + randomNum;

        } catch (Exception e) {
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (mCallBack.get() != null) {
            if (mException == null) {
                mCallBack.get().onSuccess(result);
            } else {
                mCallBack.get().onFailure(mException);
            }
        }
    }
}