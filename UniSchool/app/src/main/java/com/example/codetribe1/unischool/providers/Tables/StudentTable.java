package com.example.codetribe1.unischool.providers.Tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by CodeTRibe1 on 2015-03-29.
 */
public class StudentTable {
    public static final String STUDENTS_TABLE_NAME = "students";
    public static final String COLUMN_FIRST_NAME="firstname";
    public static final String COLUMN_MIDDLE_NAME="middlename";
    public static final String COLUMN_LAST_NAME="lastname";
    public static final String COLUMN_GRADE_ID="gradeID";
    public static final String COLUMN_SCHOOL_ID="schoolID";
    public static final String COLUMN_EMAIL="email";

    public static final String COLUMN_STUDENT_ID = "studentID";//global
    public static final String COLUMN_CP_STUDENT_ID = "studentID";//local

    public static final String DEFAULT_SORT_ORDER = "_ID Desc";

    public static final String[] DEFAULT_STUDENTS_PROJECTIONS={COLUMN_FIRST_NAME,COLUMN_MIDDLE_NAME,
            COLUMN_LAST_NAME,COLUMN_GRADE_ID,COLUMN_SCHOOL_ID,COLUMN_EMAIL,COLUMN_STUDENT_ID};

    private static final String CREATE_TABLE_QUERY = " CREATE TABLE "+STUDENTS_TABLE_NAME+" "
            +" ( "+COLUMN_CP_STUDENT_ID+" integer primary key autoincrement,"
            +COLUMN_STUDENT_ID+ " text, "
            +COLUMN_FIRST_NAME+ " text not null, "
            +COLUMN_LAST_NAME+ " text not null, "
            +COLUMN_GRADE_ID+ " text not null, "
            +COLUMN_SCHOOL_ID+ " text not null, "
            +COLUMN_EMAIL+ " text)";

    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+STUDENTS_TABLE_NAME;


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
