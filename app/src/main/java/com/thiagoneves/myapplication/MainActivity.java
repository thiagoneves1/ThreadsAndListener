package com.thiagoneves.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Callback {

    private TextView textView;
    static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

        MyClass myClass = new MyClass(this);
        T1 t = new T1(this);
        new Thread(myClass).start();
        new Thread(t).start();
    }


    class T1 implements Runnable {
        Callback c;

        public T1(Callback c) {
            this.c = c;
        }

        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    c.countMe("t1");
                }
            } catch (Exception e) {
            }
        }
    };

    class MyClass implements Runnable {

        Callback c;

        public MyClass(Callback c) {
            this.c = c;
        }

        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    c.countMe("MyClass");
                }
            } catch (Exception e) {
            }
        }
    };

    @Override
    public void countMe(String name) {
        i++;
        Log.i("#THREAD ", "countMe: " + i + " name " + name);
    }
}

interface Callback {
    void countMe(String name); // would be in any signature
}


