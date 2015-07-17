package com.prateekj.task;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.prateekj.AsyncTaskResultHandler;
import com.prateekj.activity.ListToDoActivity;
import com.prateekj.helper.DbHelper;
import com.prateekj.schema.ToDoContract;

import java.util.ArrayList;
import java.util.List;

public class ListToDoTask extends AsyncTask<Void, String, List<String>>{
    private Context context;
    private AsyncTaskResultHandler resultHandler;

    public ListToDoTask(ListToDoActivity context) {
        this.context = context;
        this.resultHandler = context;
    }

    @Override
    protected List<String> doInBackground(Void... params) {
        List<String> tasks_list = new ArrayList<String>();
        DbHelper dbHelper = new DbHelper(this.context);
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

    @Override
    protected void onPostExecute(List<String> tasks) {
        this.resultHandler.handleResult(tasks);
        super.onPostExecute(tasks);
    }

}
