package com.prateekj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.prateekj.AsyncTaskResultHandler;
import com.prateekj.R;
import com.prateekj.task.DeleteToDoTask;
import com.prateekj.task.ListToDoTask;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class ListToDoActivity extends Activity implements AsyncTaskResultHandler<String>{

    private TextView currentlySelectedTask;
    private ArrayAdapter<String> taskListAdapter;

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


    private void showTasks() {
        new ListToDoTask(this).execute();
    }

    public void expandActivity(View view) {
        TextView taskTextView = (TextView) view;
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("task_text", taskTextView.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        this.currentlySelectedTask = (TextView) ((AdapterView.AdapterContextMenuInfo) menuInfo).targetView;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.todo_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence task = this.currentlySelectedTask.getText();
        new DeleteToDoTask(this).execute(task.toString());
        return super.onContextItemSelected(item);
    }

    @Override
    public void handleResult(List<String> tasks) {
        this.taskListAdapter = new ArrayAdapter<String>(this, R.layout.todo_list_text_view, tasks);
        ListView taskListView = (ListView) findViewById(R.id.task_list);
        taskListView.setAdapter(this.taskListAdapter);
        registerForContextMenu(taskListView);
    }

    public void updateList(String task){
        this.taskListAdapter.remove(task);
        Toast.makeText(this, "Task Deleted", LENGTH_SHORT).show();
    }
}