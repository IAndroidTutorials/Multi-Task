package com.prateekj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.Integer;

public class MyActivity extends Activity
{
    private Integer counter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView counterField = (TextView)findViewById(R.id.counter);
        counterField.setText(counter.toString());
    }

    public void increaseCounter(View view) {
        TextView counterField = (TextView)findViewById(R.id.counter);
        counter++;
        counterField.setText(counter.toString());
    }
}
