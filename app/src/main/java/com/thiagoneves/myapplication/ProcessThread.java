package com.thiagoneves.myapplication;

import android.os.Handler;
import android.os.Message;

import java.util.Random;


public class ProcessThread extends Thread {
 
    private Handler handler;
    public static final int CODE_MESSAGE_RUNNING = 1;
    public static final int CODE_MESSAGE_FINISHED = 2;
 
    public ProcessThread(Handler handler) {
        this.handler = handler;
    }
     
    @Override
    public void run() {
 
        for (int i = 0; i < 100; i++) {
            Message message = new Message();
            //code for control
            message.what = CODE_MESSAGE_RUNNING;
 
            //set any type of object, in this case we define string
            if (i < 99) {
                message.obj = "Counting: " + i;
            } else {
                message.obj = "Finishing...";
            }

            //send object to handler
            handler.sendMessage(message);
 
            try {
                //simulation processing of 1seg
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        //When finish we create and set another code
        //The control is different .
        Random random = new Random();
        Message message = new Message();
        message.what = CODE_MESSAGE_FINISHED;
        message.obj = random.nextLong();

        //send object to handler
        handler.sendMessage(message);
    }
}