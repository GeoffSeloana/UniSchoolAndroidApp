package com.example.codetribe1.unischool.providers.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.codetribe1.unischool.providers.Tables.StudentTable;

/**
 * Created by CodeTRibe1 on 2015-03-29.
 */
public class StudentsDatabaseHelper extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String DATABASE_FILE = "students.db";

    public StudentsDatabaseHelper(Context context) {
        super(context, DATABASE_FILE, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StudentTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StudentTable.onUpgrade(db);
    }
}
