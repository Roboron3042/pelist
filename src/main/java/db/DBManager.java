package db;

/**
 * Autores:
 * Roberto Michán Sánchez
 * Tomás Goizueta Díaz-Parreño
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert_list(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SUBJECT, name);
        contentValue.put(DatabaseHelper.DESC, desc);
        database.insert(DatabaseHelper.LISTS_TABLE, null, contentValue);
    }

    private void insert_movie(String name, String desc, String dir, Integer year, Integer score, String date, Integer list) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SUBJECT, name);
        contentValue.put(DatabaseHelper.DESC, desc);
        contentValue.put(DatabaseHelper.DIR, dir);
        contentValue.put(DatabaseHelper.YEAR, year);
        contentValue.put(DatabaseHelper.SCORE, score);
        contentValue.put(DatabaseHelper.DATE, date);
        contentValue.put(DatabaseHelper.LIST, list);
        database.insert(DatabaseHelper.MOVIES_TABLE, null, contentValue);

    }

    public Cursor fetch_list() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC };
        Cursor cursor = database.query(DatabaseHelper.LISTS_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch_movie() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC, DatabaseHelper.DIR,
                DatabaseHelper.YEAR, DatabaseHelper.SCORE, DatabaseHelper.DATE, DatabaseHelper.LIST};
        Cursor cursor = database.query(DatabaseHelper.MOVIES_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update_list(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        return database.update(DatabaseHelper.LISTS_TABLE, contentValues, DatabaseHelper._ID + " = " + _id, null);
    }

    public int update_movie(long _id, String name, String desc, String dir, Integer year, Integer score, String date, Integer list) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        contentValues.put(DatabaseHelper.DIR, dir);
        contentValues.put(DatabaseHelper.YEAR, year);
        contentValues.put(DatabaseHelper.SCORE, score);
        contentValues.put(DatabaseHelper.DATE, date);
        contentValues.put(DatabaseHelper.LIST, list);
        return database.update(DatabaseHelper.MOVIES_TABLE, contentValues, DatabaseHelper._ID + " = " + _id, null);
    }

    public void delete_list(long _id) {
        database.delete(DatabaseHelper.LISTS_TABLE, DatabaseHelper._ID + "=" + _id, null);
    }

    public void delete_movie(long _id) {
        database.delete(DatabaseHelper.MOVIES_TABLE, DatabaseHelper._ID + "=" + _id, null);
    }

}
