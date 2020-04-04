package com.thiagoneves.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

        new Thread() { //this thread run in background
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    final int value = i;
                    try {
                        Thread.sleep(1000);
                        Toast.makeText(MainActivity.this, "Error try show in main thread", Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
