package com.prateekj;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class ListToDoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_todo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showTasks();
    }

    private List<String> retrieveTasks() {
        List<String> tasks_list = new ArrayList<String>();
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                ToDoContract.ToDo._ID,
                ToDoContract.ToDo.TASK,
                ToDoContract.ToDo.CREATED_AT
        };

        Cursor cursor = db.query(ToDoContract.ToDo.TABLE_NAME, projection, null, null, null, null, null);
        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount(); i ++) {
            String task = cursor.getString(cursor.getColumnIndex(ToDoContract.ToDo.TASK));
            cursor.moveToNext();
            tasks_list.add(task);
        }
        return tasks_list;
    }

    private void showTasks() {
        ArrayAdapter<String> tasksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, retrieveTasks());
        ListView taskListView = (ListView) findViewById(R.id.task_list);
        taskListView.setAdapter(tasksAdapter);
    }
}