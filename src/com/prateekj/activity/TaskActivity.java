package com.prateekj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.prateekj.R;

public class TaskActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        Intent intent = getIntent();
        String taskText = intent.getExtras().getString("task_text");
        TextView taskBox = (TextView) findViewById(R.id.task);
        taskBox.setText(taskText);
    }
}
