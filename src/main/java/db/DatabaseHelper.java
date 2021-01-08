package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pelist.main.R;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    // Table Name
    public static final String LISTS_TABLE = "LISTS";
    public static final String MOVIES_TABLE = "MOVIES";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";
    public static final String DIR = "director";
    public static final String YEAR = "year";
    public static final String SCORE = "score";
    public static final String DATE = "date";
    public static final String LIST = "list";

    // Database Information
    static final String DB_NAME = "PELIST.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_LISTS_TABLE = "create table " + LISTS_TABLE + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT);";
    private static final String CREATE_MOVIES_TABLE = "create table " + MOVIES_TABLE + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT, "
            + DIR + " TEXT, " + YEAR + " INTEGER, " + SCORE + " INTEGER," + DATE + " TEXT, " + LIST + " INTEGER);";


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
        db.execSQL(CREATE_LISTS_TABLE);
        db.execSQL(CREATE_MOVIES_TABLE);
        db.execSQL("insert into " + LISTS_TABLE +
                " values ('0', '" + watched + "' , '" + watched_desc + "');");
        db.execSQL("insert into " + LISTS_TABLE +
                " values ('1', '" + pending + "' , '" + pending_desc + "');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LISTS_TABLE);
        onCreate(db);
    }
}
