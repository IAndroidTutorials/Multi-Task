package com.prateekj.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
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
import com.prateekj.helper.DbHelper;
import com.prateekj.schema.ToDoContract;
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        this.currentlySelectedTask = (TextView) ((AdapterView.AdapterContextMenuInfo) menuInfo).targetView;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.todo_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence text = this.currentlySelectedTask.getText();
        deleteToDoFor(text.toString());
        return super.onContextItemSelected(item);
    }

    private void deleteToDoFor(String task) {
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = ToDoContract.ToDo.TASK + " LIKE ?";
        String[] selectionArgs = {task};
        db.delete(ToDoContract.ToDo.TABLE_NAME, selection, selectionArgs);
        this.taskListAdapter.remove(task);
        Toast.makeText(this, "Task Deleted", LENGTH_SHORT).show();
    }

    @Override
    public void handleResult(List<String> tasks) {
        this.taskListAdapter = new ArrayAdapter<String>(this, R.layout.todo_list_text_view, tasks);
        ListView taskListView = (ListView) findViewById(R.id.task_list);
        taskListView.setAdapter(this.taskListAdapter);
        registerForContextMenu(taskListView);
    }
}