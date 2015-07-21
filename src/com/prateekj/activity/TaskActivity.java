package com.prateekj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.prateekj.PostDeleteToDoTaskHandler;
import com.prateekj.R;
import com.prateekj.task.DeleteToDoTask;

import static android.widget.Toast.LENGTH_SHORT;

public class TaskActivity extends Activity implements PostDeleteToDoTaskHandler{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);
        Intent intent = getIntent();
        String taskText = intent.getExtras().getString("task_text");
        TextView taskBox = (TextView) findViewById(R.id.task);
        taskBox.setText(taskText);
    }

    public void deleteTask(View view){
        TextView taskTextView = (TextView) findViewById(R.id.task);
        new DeleteToDoTask(this).execute(taskTextView.getText().toString());
    }

    @Override
    public void handleAfterDelete(String task) {
        Toast.makeText(this, "Task Deleted", LENGTH_SHORT).show();
        Intent intent = new Intent(this, ListToDoActivity.class);
        startActivity(intent);
    }
}
