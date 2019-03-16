package edu.vcu.mymail.alberghinig.hackheist;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.v4.content.ContextCompat.getSystemService;

public class DBController extends SQLiteOpenHelper {

    private ArrayList<User> users;
    public static final String URL = "http://127.0.0.1/sqlandroidsync";
    public static final String DATA_SAVED_BROADCAST = "net.simplifiedcoding.datasaved";
    private BroadcastReceiver broadcastReceiver;

    private static final String DATABASE_NAME = "hack_heist.db";

    public static final String USER_TABLE = "users";
    public static final String USER_COL_1 = "ID";
    public static final String USER_COL_2 = "First_Name";
    public static final String USER_COL_3 = "Last_Name";
    public static final String USER_COL_4 = "Username";
    public static final String USER_COL_5 = "Email";
    public static final String USER_COL_6 = "Password";
    public static final String USER_COL_7 = "Security_Question";
    public static final String USER_COL_8 = "Security_Question_Answer";
    public static final String USER_COL_STATUS = "status";

    private static final String BADGE_TABLE = "badges_table";
    private static final String BADGES_COL_1 = "ID";
    private static final String BADGES_COL_2 = "Name";
    private static final String BADGES_COL_3 = "Description";

    private static final String KEYCARD_TABLE = "keycards_table";
    private static final String KEYCARD_COL_1 = "ID";
    private static final String KEYCARD_COL_2 = "Name";
    private static final String KEYCARD_COL_3 = "Description";

    private static final int DATABASE_VERSION = 1;

    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT, LAST_NAME TEXT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, SECURITY_QUESTION TEXT, SECURITY_QUESTION_ANSWER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public void clearLocalDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("create table " + USER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT, LAST_NAME TEXT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, SECURITY_QUESTION TEXT, SECURITY_QUESTION_ANSWER TEXT)");
    }

    public boolean insertUser(String firstName, String lastName, String username, String email, String password, String securityQ, String securityQA, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_2, firstName);
        contentValues.put(USER_COL_3, lastName);
        contentValues.put(USER_COL_4, username);
        contentValues.put(USER_COL_5, email);
        contentValues.put(USER_COL_6, password);
        contentValues.put(USER_COL_7, securityQ);
        contentValues.put(USER_COL_8, securityQA);
        long result = db.insert(USER_TABLE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateUserSecurityColumn(ActiveUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COL_7,user.getSecurityQuestion());
        cv.put(USER_COL_8,user.getSecurityQuestionAnswer());
        String ID = String.valueOf(user.getID());

        long result = db.update(USER_TABLE, cv, "ID = "+ID, null);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateUserPasswordColumn(ActiveUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COL_6,user.getPassword());
        String ID = String.valueOf(user.getID());

        long result = db.update(USER_TABLE, cv, "ID = "+ID, null);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_2, user.getFirstName());
        contentValues.put(USER_COL_3, user.getLastName());
        contentValues.put(USER_COL_4, user.getUsername());
        contentValues.put(USER_COL_5, user.getEmail());
        contentValues.put(USER_COL_6, user.getPassword());
        contentValues.put(USER_COL_7, user.getSecurityQuestion());
        contentValues.put(USER_COL_8, user.getSecurityQuestionAnswer());
        long result = db.insert(USER_TABLE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    /**
     * Get list of Users from SQLite DB as Array List
     *
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllUsersMap() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ID", cursor.getString(0));
                map.put("First_Name", cursor.getString(1));
                map.put("Last_Name", cursor.getString(2));
                map.put("Username", cursor.getString(3));
                map.put("Email", cursor.getString(4));
                map.put("Password", cursor.getString(5));
                map.put("Security_Question", cursor.getString(6));
                map.put("Security_Question_Answer", cursor.getString(7));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    public ArrayList<User> getListOfUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User newEntry = new User();
                newEntry.setID(Integer.parseInt(cursor.getString(0)));
                newEntry.setFirstName(cursor.getString(1));
                newEntry.setLastName(cursor.getString(2));
                newEntry.setUsername(cursor.getString(3));
                newEntry.setEmail(cursor.getString(4));
                newEntry.setPassword(cursor.getString(5));
                newEntry.setSecurityQuestion(cursor.getString(6));
                newEntry.setSecurityQuestionAnswer(cursor.getString(7));
                usersList.add(newEntry);
            } while (cursor.moveToNext());
        }
        database.close();
        return usersList;
    }

    /**
     * Compose JSON out of SQLite records
     *
     * @return
     */
    public String composeJSONfromSQLite() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ID", cursor.getString(0));
                map.put("First_Name", cursor.getString(1));
                map.put("Last_Name", cursor.getString(2));
                map.put("Username", cursor.getString(3));
                map.put("Email", cursor.getString(4));
                map.put("Password", cursor.getString(5));
                map.put("Security_Question", cursor.getString(6));
                map.put("Security_Question_Answer", cursor.getString(7));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        //Use GSON to serialize Array List to JSON
        Log.d("USER INFO AS JSON", gson.toJson(wordList));
        return gson.toJson(wordList);
    }
}
