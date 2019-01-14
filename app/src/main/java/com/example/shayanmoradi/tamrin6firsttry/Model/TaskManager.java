package com.example.shayanmoradi.tamrin6firsttry.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shayanmoradi.tamrin6firsttry.database.CrimeBaseHelper;
import com.example.shayanmoradi.tamrin6firsttry.database.CrimeCursorWrapper;
import com.example.shayanmoradi.tamrin6firsttry.database.CrimeSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskManager {
    private static TaskManager instance;
    ArrayList<Task> mTAskMap;
    private Context mContext;
private SQLiteDatabase mDatatase;
    private TaskManager(Context context) {
        mTAskMap = new ArrayList<>();
        mContext = context.getApplicationContext();
    mDatatase = new CrimeBaseHelper(mContext).getWritableDatabase();

    }

//    public void addTask(Task task, int ZeroForAlloneForDoneTwoForUnDone) {
//        switch (ZeroForAlloneForDoneTwoForUnDone) {
//            case 0:
//                task.setZeroForAlloneForDoneTwoForUnDone(0);
//                mTAskMap.add(task);
//                break;
//            case 1:
//                mTAskMap.add(task);
//                task.setZeroForAlloneForDoneTwoForUnDone(1);
//                break;
//            case 2:
//                mTAskMap.add(task);
//                task.setZeroForAlloneForDoneTwoForUnDone(2);
//                break;
//        }
//    }

    public void deleteTask(Task task, int ZeroForAlloneForDoneTwoForUnDone) {
        switch (ZeroForAlloneForDoneTwoForUnDone) {
            case 0:
                task.setZeroForAlloneForDoneTwoForUnDone(0);
                mTAskMap.add(task);
                break;
            case 1:
                mTAskMap.add(task);
                task.setZeroForAlloneForDoneTwoForUnDone(1);
                break;
            case 2:
                mTAskMap.add(task);
                task.setZeroForAlloneForDoneTwoForUnDone(2);
                break;
        }
    }

    public void deleteTask(Task task) {
        mTAskMap.remove(task);
    }


    public static TaskManager getInstance(Context context) {
        if (instance == null)
            instance = new TaskManager(context);
        return instance;
    }

//    public List<Task> getTasks() {
//        List<Task> valueList = mTAskMap;
//        return valueList;
//    }
    ////////database
    public List<Task> getAllTasks() {
        List<Task> crimes = new ArrayList<>();
        CrimeCursorWrapper crimeCursorWrapper = queryCrime(null, null);
        if (crimeCursorWrapper.getCount() == 0)
            return crimes;
        crimeCursorWrapper.moveToFirst();
        try {

            while (!crimeCursorWrapper.isAfterLast()) {

                crimes.add(crimeCursorWrapper.getCrime());
                crimeCursorWrapper.moveToNext();
            }
        } finally {
            crimeCursorWrapper.close();
        }
        return crimes;
    }


    public Task getask(UUID id) {

        String whereClause = CrimeSchema.CrimeTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        Task crime;
        CrimeCursorWrapper cursor = queryCrime(whereClause, whereArgs);
        if (cursor.getCount()==0)
            return null;
        cursor.moveToFirst();
        try {
            crime=cursor.getCrime();



        } finally {
            cursor.close();
        }

        return crime;
    }

    public ContentValues getContentValues(Task crime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeSchema.CrimeTable.Cols.UUID, crime.getmTaskId().toString());
        contentValues.put(CrimeSchema.CrimeTable.Cols.TITLE, crime.getTitle());
        contentValues.put(CrimeSchema.CrimeTable.Cols.DES, crime.getmDescription());
        contentValues.put(CrimeSchema.CrimeTable.Cols.TIME_SIMPLE, crime.getSimpleTime());
        contentValues.put(CrimeSchema.CrimeTable.Cols.DATE_SIMPLE, crime.getSimpleDate());
        contentValues.put(CrimeSchema.CrimeTable.Cols.DATE, crime.getmDate().getTime());
        contentValues.put(CrimeSchema.CrimeTable.Cols.DONE, crime.getZeroForAlloneForDoneTwoForUnDone());


        return contentValues;
    }
    private CrimeCursorWrapper queryCrime(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatatase.query(CrimeSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new CrimeCursorWrapper(cursor);
    }
    public void addTask(Task crime,int test) {
        ContentValues values = getContentValues(crime);
        mDatatase.insert(CrimeSchema.CrimeTable.NAME, null, values);
    }
    public void update(Task crime) {
        ContentValues values = getContentValues(crime);
        // String whereClause = CrimeSchema.CrimeTable.Cols.UUID + " = ?AND _id > ?";
        String whereClause = CrimeSchema.CrimeTable.Cols.UUID + " = ?";
        mDatatase.update(CrimeSchema.CrimeTable.NAME
                , values
                , whereClause
                , new String[]{crime.getmTaskId().toString()});
    }
    //database
//    public List<Task> getAllTasks() {
//        List<Task> valueList = mTAskMap;
//        return valueList;
//    }

    public List<Task> getDoneTasks() {

        List<Task> valueList = new ArrayList<>();

        for (int i = 0; i < mTAskMap.size(); i++) {
            if (mTAskMap.get(i).getZeroForAlloneForDoneTwoForUnDone() == 1) {
                valueList.add(mTAskMap.get(i));
            }

        }
        return valueList;
    }

    public List<Task> getUndoneTasks() {
        List<Task> valueList = new ArrayList<>();
        ;
        for (int i = 0; i < mTAskMap.size(); i++) {
            if (mTAskMap.get(i).getZeroForAlloneForDoneTwoForUnDone() != 1) {
                valueList.add(mTAskMap.get(i));
            }

        }
        return valueList;
    }

    //    public Task getTask(UUID id) {
//        Task task;
//        if (mTAskMap.containsValue(id))
//            return mTAskMap.get(id);
//        return null;
//    }
//    public Task getask(UUID id) {
//        if (mTAskMap.get(getIndexOfTask(id)) != null)
//            return mTAskMap.get(getIndexOfTask(id));
//        return null;
//    }


    public int getIndexOfTask(UUID id) {
        List<Task> task = getAllTasks();

        for (int i = 0; i < task.size(); i++) {
            if (task.get(i).getmTaskId().equals(id))
                return i;
        }
        return -1;
    }

}