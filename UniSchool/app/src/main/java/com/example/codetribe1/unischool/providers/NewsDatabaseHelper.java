package com.example.codetribe1.unischool.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CodeTRibe1 on 2015-03-28.
 */
public class NewsDatabaseHelper extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String DATABASE_FILE="news.db";

    public NewsDatabaseHelper(Context context) {
        super(context,DATABASE_FILE,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        NewsTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        NewsTable.onUpgrade(db);
    }
}
