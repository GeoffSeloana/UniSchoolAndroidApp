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
 * Created by CodeTRibe1 on 2015-03-28.
 */
public class NewsContentProvider  extends ContentProvider {
    private static final String TAG = NewsContentProvider.class.getSimpleName();
    private NewsDatabaseHelper database;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(NewsContentProviderUtil.NEWS_AUTHORITY,NewsContentProviderUtil.NEWS_BASE_PATH,NewsContentProviderUtil.QUERY_TYPE_LIST);
        uriMatcher.addURI(NewsContentProviderUtil.NEWS_AUTHORITY,NewsContentProviderUtil.NEWS_BASE_PATH +"/#",NewsContentProviderUtil.QUERY_TYPE_BY_COLUMN_ID);
        uriMatcher.addURI(NewsContentProviderUtil.NEWS_AUTHORITY,NewsContentProviderUtil.NEWS_BASE_PATH +"/studentID/*",NewsContentProviderUtil.QUERY_TYPE_BY_STUDENT_ID);
    }


    @Override
    public boolean onCreate() {
        database = new NewsDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(NewsTable.NEWS_TABLE_NAME);
        //Is this a query by ID or by Global student ID
        int requestType= uriMatcher.match(uri);
        switch (requestType){
            case NewsContentProviderUtil.QUERY_TYPE_LIST:
                //retrieving all --> select * from students

                break;
            case NewsContentProviderUtil.QUERY_TYPE_BY_COLUMN_ID:
                //retrieving specific student --> select * form students where ID=x
                queryBuilder.appendWhere(NewsTable.COLUMN_CP_NEWS_ID+"="+uri.getLastPathSegment());
                break;
            case NewsContentProviderUtil.QUERY_TYPE_BY_STUDENT_ID:
                //retriving a student with specific global ID
                queryBuilder.appendWhere(NewsTable.COLUMN_NEWS_ID+"="+uri.getLastPathSegment());
                break;
            default:
                break;
        }
        Cursor cursor=null;
        SQLiteDatabase db = database.getWritableDatabase();
        try{
            cursor = queryBuilder.query(db,projection, selection,selectionArgs,null,null,NewsTable.DEFAULT_SORT_ORDER);
            if(cursor != null){ cursor.setNotificationUri(getContext().getContentResolver(), uri);}
        }catch (Exception e){
            Log.e(TAG, "Error while retrieving New(s) ", e);
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
        if(requestType != NewsContentProviderUtil.QUERY_TYPE_LIST){
            throw new IllegalArgumentException("Invalid URI pattern for insert Operation.");
        }
        SQLiteDatabase db = database.getWritableDatabase();
        long newNewsLocalID = db.insert(NewsTable.NEWS_TABLE_NAME,null,values);

        getContext().getContentResolver().notifyChange(uri,null);
        Uri newNewsURI = Uri.parse(NewsContentProviderUtil.NEWS_CONTENT_URI.toString()+"/"+newNewsLocalID);
        Toast.makeText(getContext(), "Added News URI:: " + newNewsURI.toString(), Toast.LENGTH_LONG).show();
        return newNewsURI;
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
