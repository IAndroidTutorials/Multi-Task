package com.prateekj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.prateekj.R;
import com.prateekj.task.CreateToDoTask;

public class CreateToDoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_todo);
    }

    public void saveTask(View view) {
        EditText taskBox = (EditText)findViewById(R.id.task_box);
        String task = taskBox.getText().toString();
        if ("".equals(task)) return;
        new CreateToDoTask(this, taskBox).execute(task);
    }

    public void showAllTasks(View view) {
        Intent intent = new Intent(this, ListToDoActivity.class);
        startActivity(intent);
    }
}
