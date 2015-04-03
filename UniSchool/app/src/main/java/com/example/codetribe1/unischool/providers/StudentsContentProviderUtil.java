package com.example.codetribe1.unischool.providers;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.codetribe1.unischool.dto.StudentDTO;

/**
 * Created by CodeTRibe1 on 2015-03-29.
 */
public class StudentsContentProviderUtil {

    private static final String TAG = StudentsContentProviderUtil.class.getSimpleName();


    public static final String STUDENTS_AUTHORITY = "com.example.codetribe1.unischool.providers";
    public static final String STUDENTS_BASE_PATH = "students";
    public static final Uri STUDENTS_CONTENT_URI = Uri.parse("content://"+STUDENTS_AUTHORITY+"/"+STUDENTS_BASE_PATH);

    //These are literal values for Content-Resolver Query Types
    //Query Types
    public static final int QUERY_TYPE_BY_COLUMN_ID = 1;
    public static final int QUERY_TYPE_BY_STUDENT_ID = 2;
    public static final int QUERY_TYPE_LIST = 3;

    //----------get list of students---------------------------
    public static StudentDTO getStudent(ContentResolver contentResolver){
        StudentDTO student = new StudentDTO();
        Cursor studentsListCursor = contentResolver.query(STUDENTS_CONTENT_URI, StudentTable.DEFAULT_STUDENTS_PROJECTIONS,null,null,StudentTable.DEFAULT_SORT_ORDER);
        if(studentsListCursor != null){
            //we have the data, now we're iterating
            while(studentsListCursor.moveToNext()){
                //get values from the cursor
                student = fromCursor(studentsListCursor);

            }
        }

        return student;
    }

    //----------add student-------------------------------------
    public static Uri addStudent(ContentResolver contentResolver, StudentDTO studentDataObject){
        Log.i(TAG, "Adding Student:: \n " + studentDataObject);
        Uri newStudentUri = null;
        ContentValues newStudentValues = fromStudentDTO(studentDataObject);
        if(newStudentValues != null){
            //use content-resolver to save into CP
            newStudentUri = contentResolver.insert(STUDENTS_CONTENT_URI,newStudentValues);
        }
        return newStudentUri;

    }


    //---------get student from cursor--------------------------------
    private static StudentDTO fromCursor(Cursor cursor){
        String firstName=cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_FIRST_NAME));
        String middleName=cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_MIDDLE_NAME));
        String lastName=cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_LAST_NAME));
        int gradeID =cursor.getInt(cursor.getColumnIndex(StudentTable.COLUMN_GRADE_ID));
        int schoolID =cursor.getInt(cursor.getColumnIndex(StudentTable.COLUMN_SCHOOL_ID));
        String email=cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_EMAIL));
        String globalStudentId= cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_STUDENT_ID));


        StudentDTO student = new StudentDTO(0,firstName,middleName,lastName,email,schoolID,gradeID);
        return  student;
    }
    //-----------get values to insert------------------------------------
    public static ContentValues fromStudentDTO(StudentDTO studentDataObject){
        if(studentDataObject==null){ return null;}

        ContentValues valuesToInsert = new ContentValues();
        valuesToInsert.put(StudentTable.COLUMN_FIRST_NAME,studentDataObject.getFirstName());
        valuesToInsert.put(StudentTable.COLUMN_MIDDLE_NAME,studentDataObject.getMiddleName());
        valuesToInsert.put(StudentTable.COLUMN_LAST_NAME,studentDataObject.getLastName());
        valuesToInsert.put(StudentTable.COLUMN_EMAIL,studentDataObject.getEmail());
        valuesToInsert.put(StudentTable.COLUMN_GRADE_ID,studentDataObject.getGradeID());
        valuesToInsert.put(StudentTable.COLUMN_SCHOOL_ID,studentDataObject.getSchoolID());

        return valuesToInsert;
    }
}
