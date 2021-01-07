package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pelist.main.R;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    // Table Name
    public static final String TABLE_NAME = "LISTAS";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";

    // Database Information
    static final String DB_NAME = "PELIST.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT);";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String watched= context.getString(R.string.watched_movies_list);
        String watched_desc = context.getString(R.string.watched_movies_list_description);
        String pending= context.getString(R.string.pending_movies_list);
        String pending_desc = context.getString(R.string.pending_movies_list_description);
        db.execSQL(CREATE_TABLE);
        db.execSQL("insert into " + TABLE_NAME +
                " values ('0', '" + watched + "' , '" + watched_desc + "');");
        db.execSQL("insert into " + TABLE_NAME +
                " values ('1', '" + pending + "' , '" + pending_desc + "');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
