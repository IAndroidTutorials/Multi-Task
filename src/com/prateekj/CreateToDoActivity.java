package com.prateekj;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ToDoContract.ToDo.TASK, task);
        values.put(ToDoContract.ToDo.CREATED_AT, new Date().toString());

        db.insert(ToDoContract.ToDo.TABLE_NAME, "null", values);
        Log.e("INSERT", "Successfully Inserted");

        taskBox.setText("");
        Toast.makeText(this, "Task Created", LENGTH_SHORT).show();
    }

    public void showAllTasks(View view) {
        Intent intent = new Intent(this, ListToDoActivity.class);
        startActivity(intent);
    }
}
