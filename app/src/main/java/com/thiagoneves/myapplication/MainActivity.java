package com.thiagoneves.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnEventListener<String> {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

        SomeTask someTask = new SomeTask(this);
        someTask.execute();
    }

    @Override
    public void onSuccess(String result) {
        textView.setText(result);
    }

    @Override
    public void onFailure(Exception e) {
        textView.setText(e.getLocalizedMessage());
    }
}
