package a00906732.bcit.ca.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ha on 11/23/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "AssignmentAlarm.db";
    public static final int    DB_VERSION = 1;
    private static final String LOG = "DatabaseHelper";

    public static final String TABLE_USERS      = "user";
    public static final String COLUMN_USERNAME  = "username";
    public static final String COLUMN_PASSWORD  = "password";

    public static final String TABLE_TASKS          = "task";
    public static final String COLOUMN_TASKNAME     = "taskname";
    public static final String COLOUMN_COURSENAME   = "coursename";
    public static final String COLOUMN_MARKWEIGHT   = "markweight";
    public static final String COLOUMN_REPEAT       = "repeat";
    public static final String COLOUMN_DUEDATE      = "duedate";

    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //make User Table
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL);");

        //make Task Table
        db.execSQL("CREATE TABLE " + TABLE_TASKS + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLOUMN_TASKNAME + " TEXT NOT NULL, " +
                COLOUMN_COURSENAME + " TEXT NOT NULL, " +
                COLOUMN_MARKWEIGHT + " INTEGER NOT NULL, " +
                COLOUMN_REPEAT + " TEXT NOT NULL, " +
                "username" + " TEXT NOT NULL, " +
                COLOUMN_DUEDATE + " TEXT NOT NULL);");

        this.db = db;
    }

    /**
     * insert user object from register activity to this database
     * @param u username and password
     */
    public void insertUser(Users u)
    {
        db = this.getWritableDatabase();
        ContentValues userValue = new ContentValues();
        userValue.put(COLUMN_USERNAME, u.getUsername());
        userValue.put(COLUMN_PASSWORD, u.getPassword());
        db.insert(TABLE_USERS, null, userValue);
        db.close();
    }

    /*
    * Insert task from AddView activity to Task Table.
    */
    public void insertTask(Tasks task){
        db = this.getWritableDatabase();
        ContentValues taskValue = new ContentValues();
        taskValue.put(COLOUMN_TASKNAME    , task.getTaskname());
        taskValue.put(COLOUMN_COURSENAME  , task.getCourse());
        taskValue.put(COLOUMN_MARKWEIGHT, task.getWeight());
        taskValue.put(COLOUMN_REPEAT      , task.getRepeat());
        taskValue.put("username"          , MainActivity.username);
        taskValue.put(COLOUMN_DUEDATE, task.getDuedate());
        db.insert(TABLE_TASKS, null, taskValue);
        db.close();
    }

    /*
    * Get single Task.
    * @param task_id
    * @return
    */
    public Tasks[] getTask(String taskDate) {
        Tasks result[] = {};
        int i=0;
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " WHERE "
                + "duedate = ? AND username = ?";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, new String[]{taskDate, MainActivity.username});

        if (c == null) {
            return null;
        }

        c.moveToFirst();
        result = new Tasks[c.getCount()];
        while(!c.isAfterLast()){
            Tasks td = new Tasks();
            td.setTaskname(c.getString(c.getColumnIndex(COLOUMN_TASKNAME)));
            td.setCourse((c.getString(c.getColumnIndex(COLOUMN_COURSENAME))));
            td.setWeight(c.getInt(c.getColumnIndex(COLOUMN_MARKWEIGHT)));
            td.setRepeat(c.getString(c.getColumnIndex(COLOUMN_REPEAT)));
            td.setDuedate(c.getString(c.getColumnIndex(COLOUMN_DUEDATE)));
            result[i++] = td;
            c.moveToNext();
        }
//        Log.d("kitty", Integer.toString(result.length));
        return result;
    }

    /**
     * Get all Tasks.
     * @return
     */
    public List<Tasks> getAllTasks() {
        List<Tasks> tasks = new ArrayList<Tasks>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Tasks td = new Tasks();
                td.setTaskname(c.getString(c.getColumnIndex(COLOUMN_TASKNAME)));
                td.setCourse((c.getString(c.getColumnIndex(COLOUMN_COURSENAME))));
                td.setWeight(c.getInt(c.getColumnIndex(COLOUMN_MARKWEIGHT)));
                td.setRepeat(c.getString(c.getColumnIndex(COLOUMN_REPEAT)));
                td.setDuedate(c.getString(c.getColumnIndex(COLOUMN_DUEDATE)));

                // adding to task list
                tasks.add(td);
            } while (c.moveToNext());
        }
        return tasks;
    }

    /**
     * Get all Tasks by date (date is a String for now).
     * @param date
     * @return
     */
    public List<Tasks> getAllTasksByDate(String date) {
        List<Tasks> tasks = new ArrayList<Tasks>();

//        String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " td, "
//                + TABLE_TAG + " tg, " + TABLE_TODO_TAG + " tt WHERE tg."
//                + KEY_TAG_NAME + " = '" + tag_name + "'" + " AND tg." + KEY_ID
//                + " = " + "tt." + KEY_TAG_ID + " AND td." + KEY_ID + " = "
//                + "tt." + KEY_TODO_ID;

        String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " WHERE "
                + COLOUMN_DUEDATE + " = " + date;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Tasks td = new Tasks();
                td.setTaskname(c.getString(c.getColumnIndex(COLOUMN_TASKNAME)));
                td.setCourse((c.getString(c.getColumnIndex(COLOUMN_COURSENAME))));
                td.setWeight(c.getInt(c.getColumnIndex(COLOUMN_MARKWEIGHT)));
                td.setRepeat(c.getString(c.getColumnIndex(COLOUMN_REPEAT)));
                td.setDuedate(c.getString(c.getColumnIndex(COLOUMN_DUEDATE)));

                // adding to task list
                tasks.add(td);
            } while (c.moveToNext());
        }

        return tasks;
    }

    /**
     * Delete a task by id TEMPORARY, will delete by (name,course,date)
     * @param task_id
     */
    public void deleteTask(long task_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, "_id" + " = ?",
                new String[] { String.valueOf(task_id) });
    }

    /**
     * take username from main activity and search in database
     * @param username username
     * @return password or "not found"
     */
    public String searchPassword(String username){
        db = this.getReadableDatabase();
        String query = "select username, password from " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        String user;
        String pass = "not found";
        if(cursor.moveToFirst()){
            do{
                user = cursor.getString(0);
                if(user.equals(username)){
                    pass = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return pass;
    }

    /**
     * unused
     * @param username
     * @return
     */
    public boolean userExists(String username){
        String query = "SELECT username FROM user WHERE username = " + username;
        Cursor c = db.rawQuery(query,null);
        if(c.getCount() <= 0)
            return false;
        c.close();
        return true;
    }

    /**
     * Closes the database.
     */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_TASKS);
        this.onCreate(db);
    }
}
