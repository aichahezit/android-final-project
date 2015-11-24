package a00906732.bcit.ca.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ha on 11/23/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "AssignmetAlarm.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL);");
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
        db.insert(TABLE_NAME, null, userValue);
        db.close();
    }

    /**
     * take username from main activity and search in database
     * @param username username
     * @return password or "not found"
     */
    public String searchPassword(String username){
        db = this.getReadableDatabase();
        String query = "select username, password from " + TABLE_NAME;
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
