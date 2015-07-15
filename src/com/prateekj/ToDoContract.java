package com.prateekj;

import android.provider.BaseColumns;

import java.lang.String;
import java.util.Date;

public final class ToDoContract {

    public ToDoContract() {}

    public static abstract class ToDo implements BaseColumns {
        public static final String TABLE_NAME = "TODO";
        public static final String TASK = "task";
        public static final String CREATED_AT = "created_at";
    }

    private static final String COMMA_SEP = ",";
    private static final String TEXT_TYPE = " TEXT";

    public static final String SQL_CREATE_TODO =
            "CREATE TABLE IF NOT EXISTS " + ToDo.TABLE_NAME + " (" +
                    ToDo._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    ToDo.TASK + TEXT_TYPE + COMMA_SEP +
                    ToDo.CREATED_AT + TEXT_TYPE +
            " )";

    public static final String SQL_DELETE_TODO =
            "DROP TABLE IF EXISTS " + ToDo.TABLE_NAME;
}