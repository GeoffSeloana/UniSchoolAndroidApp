package com.example.codetribe1.unischool.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CodeTRibe1 on 2015-03-29.
 */
public class StudentsContentProvider extends ContentProvider {

    private static final String TAG = StudentsContentProvider.class.getSimpleName();
    private StudentsDatabaseHelper database;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(StudentsContentProviderUtil.STUDENTS_AUTHORITY,StudentsContentProviderUtil.STUDENTS_BASE_PATH,StudentsContentProviderUtil.QUERY_TYPE_LIST);
        uriMatcher.addURI(StudentsContentProviderUtil.STUDENTS_AUTHORITY,StudentsContentProviderUtil.STUDENTS_BASE_PATH +"/#",StudentsContentProviderUtil.QUERY_TYPE_BY_COLUMN_ID);
        uriMatcher.addURI(StudentsContentProviderUtil.STUDENTS_AUTHORITY,StudentsContentProviderUtil.STUDENTS_BASE_PATH +"/studentID/*",StudentsContentProviderUtil.QUERY_TYPE_BY_STUDENT_ID);
    }

    @Override
    public boolean onCreate() {
        database = new StudentsDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(StudentTable.STUDENTS_TABLE_NAME);
        //Is this a query by ID or by Global student ID
        int requestType= uriMatcher.match(uri);
        switch (requestType){
            case StudentsContentProviderUtil.QUERY_TYPE_LIST:
                //retrieving all --> select * from students

                break;
            case StudentsContentProviderUtil.QUERY_TYPE_BY_COLUMN_ID:
                //retrieving specific student --> select * form students where ID=x
                queryBuilder.appendWhere(StudentTable.COLUMN_CP_STUDENT_ID+"="+uri.getLastPathSegment());
                break;
            case StudentsContentProviderUtil.QUERY_TYPE_BY_STUDENT_ID:
                //retriving a student with specific global ID
                queryBuilder.appendWhere(StudentTable.COLUMN_STUDENT_ID+"="+uri.getLastPathSegment());
                break;
            default:
                break;
        }
        Cursor cursor=null;
        SQLiteDatabase db = database.getWritableDatabase();
        try{
            cursor = queryBuilder.query(db,projection, selection,selectionArgs,null,null,StudentTable.DEFAULT_SORT_ORDER);
            if(cursor != null){ cursor.setNotificationUri(getContext().getContentResolver(), uri);}
        }catch (Exception e){
            Log.e(TAG, "Error while retrieving Student(s) ", e);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int requestType = uriMatcher.match(uri);
        if(requestType != StudentsContentProviderUtil.QUERY_TYPE_LIST){
            throw new IllegalArgumentException("Invalid URI pattern for insert Operation.");
        }
        SQLiteDatabase db = database.getWritableDatabase();
        long newStudentLocalID = db.insert(StudentTable.STUDENTS_TABLE_NAME,null,values);

        getContext().getContentResolver().notifyChange(uri,null);
        Uri newStudentURI = Uri.parse(StudentsContentProviderUtil.STUDENTS_CONTENT_URI.toString()+"/"+newStudentLocalID);
        Toast.makeText(getContext(), "Added Student URI:: " + newStudentURI.toString(), Toast.LENGTH_LONG).show();
        return newStudentURI;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
