package com.thiagoneves.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import static com.thiagoneves.myapplication.ProcessThread.CODE_MESSAGE_FINISHED;
import static com.thiagoneves.myapplication.ProcessThread.CODE_MESSAGE_RUNNING;

public class MainActivity extends AppCompatActivity implements UDPListenerService {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

        ProcessThread processThread = new ProcessThread(new IncomingHandler(this));
        processThread.start();
    }

    @Override
    public void handleMessage(Message message) {
        updateUI(message);
    }

    static class IncomingHandler extends Handler {
        private final WeakReference<UDPListenerService> mService;

        IncomingHandler(UDPListenerService service) {
            mService = new WeakReference<>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            UDPListenerService service = mService.get();
            if (mService.get() != null) {
                service.handleMessage(msg);
            }
        }
    }

    private void updateUI(Message msg) {

        if (msg.what == CODE_MESSAGE_RUNNING) {
            //Convert object to string, typeOf result expected
            String text = (String) msg.obj;
            //set text in textview
            textView.setText(text);

        } else if (msg.what == CODE_MESSAGE_FINISHED) { //finish processing

            long value = (long) msg.obj;
            textView.setText("Finish processing, the random value is " + value);

            Toast.makeText(this, "Android Handler - Finish", Toast.LENGTH_LONG).show();
        }
    }
}


