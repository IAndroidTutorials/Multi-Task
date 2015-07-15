package com.prateekj;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class CreateToDoTask extends AsyncTask<String, Void, Void>{

    private Context context;
    private EditText taskBox;

    public CreateToDoTask(Context context, EditText taskBox) {
        this.context = context;
        this.taskBox = taskBox;
    }

    @Override
    protected Void doInBackground(String... params) {
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ToDoContract.ToDo.TASK, Arrays.asList(params).get(0));
        values.put(ToDoContract.ToDo.CREATED_AT, new Date().toString());

        db.insert(ToDoContract.ToDo.TABLE_NAME, "null", values);
        Log.e("INSERT", "Successfully Inserted");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("DEBUG", "GOING TO CHANGE UI");
        this.taskBox.setText("");
        Toast.makeText(this.context, "Task Created", LENGTH_SHORT).show();

    }
}
