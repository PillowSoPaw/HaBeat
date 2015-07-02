package com.habeat.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.habeat.Craving;
import com.habeat.CravingType;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class SQLiteCravingDAO extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "habeat";

    private static final String TABLE_NAME = "user_cravings";
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "craving_type";
    private static final String KEY_DATETIME = "datetime_occurred";


    public SQLiteCravingDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TYPE + " TEXT," + KEY_DATETIME
                + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void createCraving(Craving craving) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        CravingType type = craving.getType();
        values.put(KEY_TYPE, type.name());
        values.put(KEY_DATETIME, convertDateTime(craving.getDateTime()));

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public int getCravingsCount() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int ret = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return ret;
    }

    public List<Craving> getAllCravings() {
        List<Craving> ret = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                CravingType type = CravingType.valueOf(cursor.getString(1));
                DateTime dateTime = convertDateTime(cursor.getString(2));
                ret.add(new Craving(type, dateTime));
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return ret;
    }

    public Craving getLastCraving() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY "
                + KEY_ID + " DESC", null);
        Craving ret = null;
        if (cursor.moveToFirst()) {
            CravingType type = CravingType.valueOf(cursor.getString(1));
            DateTime dateTime = convertDateTime(cursor.getString(2));
            ret = new Craving(type, dateTime);
        }
        return ret;
    }

    public static DateTime convertDateTime(String dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.parseDateTime(dateTime);
    }

    public static String convertDateTime(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat
                .forPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.print(dateTime);
    }

}
