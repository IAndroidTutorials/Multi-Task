package com.prateekj.task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.prateekj.PostDeleteToDoTaskHandler;
import com.prateekj.helper.DbHelper;
import com.prateekj.schema.ToDoContract;

public class DeleteToDoTask extends AsyncTask<String, Void, String>{

    private PostDeleteToDoTaskHandler postDeleteToDoTaskHandler;

    public DeleteToDoTask(PostDeleteToDoTaskHandler postDeleteToDoTaskHandler) {
        this.postDeleteToDoTaskHandler = postDeleteToDoTaskHandler;
    }

    @Override
    protected String doInBackground(String... params) {
        String task = params[0];
        DbHelper dbHelper = new DbHelper((Context)this.postDeleteToDoTaskHandler);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = ToDoContract.ToDo.TASK + " LIKE ?";
        String[] selectionArgs = {task};
        db.delete(ToDoContract.ToDo.TABLE_NAME, selection, selectionArgs);
        return task;
    }

    @Override
    protected void onPostExecute(String task) {
        this.postDeleteToDoTaskHandler.handleAfterDelete(task);
    }
}
