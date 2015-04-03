package com.example.codetribe1.unischool.providers;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.codetribe1.unischool.dto.NewsDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeTRibe1 on 2015-03-28.
 */
public class NewsContentProviderUtil {
    private static final String TAG = NewsContentProviderUtil.class.getSimpleName();

    public static final String NEWS_AUTHORITY = "com.example.codetribe1.unischool.providers";
    public static final String NEWS_BASE_PATH = "news";
    public static final Uri NEWS_CONTENT_URI = Uri.parse("content://"+NEWS_AUTHORITY+"/"+NEWS_BASE_PATH);

    //These are literal values for Content-Resolver Query Types
    //Query Types
    public static final int QUERY_TYPE_BY_COLUMN_ID = 1;
    public static final int QUERY_TYPE_BY_STUDENT_ID = 2;
    public static final int QUERY_TYPE_LIST = 3;

    //----------get list of NEWS---------------------------
    public static List<NewsDTO> getStudents(ContentResolver contentResolver){
        List<NewsDTO> newsList = new ArrayList<NewsDTO>();
        Cursor newsListCursor = contentResolver.query(NEWS_CONTENT_URI, NewsTable.DEFAULT_NEWS_PROJECTIONS,null,null,NewsTable.DEFAULT_SORT_ORDER);
        if(newsListCursor != null){
            //we have the data, now we're iterating
            while(newsListCursor.moveToNext()){
                //get values from the cursor
                NewsDTO news = fromCursor(newsListCursor);
                newsList.add(news);
            }
        }

        return newsList;
    }

    //----------add student-------------------------------------
    public static Uri addStudent(ContentResolver contentResolver, NewsDTO DataObject){
        Log.i(TAG, "Adding News:: \n " + DataObject);
        Uri newNewsUri = null;
        ContentValues newNewsValues = fromNewsDTO(DataObject);
        if(newNewsValues != null){
            //use content-resolver to save into CP
            newNewsUri = contentResolver.insert(NEWS_CONTENT_URI,newNewsValues);
        }
        return newNewsUri;

    }


    //---------get news from cursor--------------------------------
    private static NewsDTO fromCursor(Cursor cursor){
        String title=cursor.getString(cursor.getColumnIndex(NewsTable.COLUMN_TITLE));
        String subTitle=cursor.getString(cursor.getColumnIndex(NewsTable.COLUMN_SUBTITLE));
        String details=cursor.getString(cursor.getColumnIndex(NewsTable.COLUMN_DETAILS));
        double longitude=cursor.getDouble(cursor.getColumnIndex(NewsTable.COLUMN_LONGITUDE));
        double latitude=cursor.getDouble(cursor.getColumnIndex(NewsTable.COLUMN_LATITUDE));
        int schoolID = cursor.getInt(cursor.getColumnIndex(NewsTable.COLUMN_SCHOOLID));



        NewsDTO news = new NewsDTO(0,title,subTitle,details,longitude,latitude,schoolID);
        return  news;
    }
    //-----------get values to insert------------------------------------
    public static ContentValues fromNewsDTO(NewsDTO DataObject){
        if(DataObject==null){ return null;}

        ContentValues valuesToInsert = new ContentValues();
        valuesToInsert.put(NewsTable.COLUMN_TITLE,DataObject.getTitle());
        valuesToInsert.put(NewsTable.COLUMN_SUBTITLE,DataObject.getSubTitle());
        valuesToInsert.put(NewsTable.COLUMN_DETAILS,DataObject.getDetails());
        valuesToInsert.put(NewsTable.COLUMN_LONGITUDE,DataObject.getLongitude());
        valuesToInsert.put(NewsTable.COLUMN_LATITUDE,DataObject.getLatitude());
        valuesToInsert.put(NewsTable.COLUMN_SCHOOLID,DataObject.getSchoolID());
        return valuesToInsert;
    }



}
