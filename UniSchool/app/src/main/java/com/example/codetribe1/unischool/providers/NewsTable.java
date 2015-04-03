package com.example.codetribe1.unischool.providers;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by CodeTRibe1 on 2015-03-28.
 */
public class NewsTable {
    public static final String NEWS_TABLE_NAME = "news";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SUBTITLE = "subTitle";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_SCHOOLID = "schoolID";
    // global ID
    public static final String COLUMN_NEWS_ID = "newsID";
    // local ID CP
    public static final String COLUMN_CP_NEWS_ID = "_ID";

    public static final String DEFAULT_SORT_ORDER = "_ID Desc";

    public static final String[] DEFAULT_NEWS_PROJECTIONS={COLUMN_TITLE,COLUMN_SUBTITLE,COLUMN_DETAILS,COLUMN_LONGITUDE,
     COLUMN_LATITUDE,COLUMN_SCHOOLID,COLUMN_NEWS_ID};

    private static final String CREATE_TABLE_QUERY = " CREATE TABLE "+NEWS_TABLE_NAME+" "
            +" ( "+COLUMN_CP_NEWS_ID+" integer primary key autoincrement,"
            +COLUMN_NEWS_ID+ " text, "
            +COLUMN_TITLE+ " text not null, "
            +COLUMN_SUBTITLE+ " text not null, "
            +COLUMN_DETAILS+ " text not null, "
            +COLUMN_LONGITUDE+ " double not null, "
            +COLUMN_LATITUDE+ " double not null, "
            +COLUMN_SCHOOLID+ " integer not null default 0)";

    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+NEWS_TABLE_NAME;

    public static void onCreate(SQLiteDatabase database){
        //create a database/table
        database.execSQL(CREATE_TABLE_QUERY);
    }

    public static void onUpgrade(SQLiteDatabase database){
        //update the database/table
        database.execSQL(DROP_TABLE_QUERY);
        onCreate(database);
    }
}
