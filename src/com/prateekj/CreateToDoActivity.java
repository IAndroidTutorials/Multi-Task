package com.prateekj;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class CreateToDoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_todo);
    }

    public void saveTask(View view) {
        EditText taskBox = (EditText)findViewById(R.id.task_box);
        String task = taskBox.getText().toString();
        new CreateToDoTask(this, taskBox).execute(task);
    }

    public void showAllTasks(View view) {
        Intent intent = new Intent(this, ListToDoActivity.class);
        startActivity(intent);
    }
}
