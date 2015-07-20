package com.prateekj.task;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.prateekj.activity.ListToDoActivity;
import com.prateekj.helper.DbHelper;
import com.prateekj.schema.ToDoContract;

public class DeleteToDoTask extends AsyncTask<String, Void, String>{

    private ListToDoActivity activity;

    public DeleteToDoTask(ListToDoActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        String task = params[0];
        DbHelper dbHelper = new DbHelper(this.activity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = ToDoContract.ToDo.TASK + " LIKE ?";
        String[] selectionArgs = {task};
        db.delete(ToDoContract.ToDo.TABLE_NAME, selection, selectionArgs);
        return task;
    }

    @Override
    protected void onPostExecute(String task) {
        this.activity.updateList(task);
    }
}
