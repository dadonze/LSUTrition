package csc4330.lsutrition.OrderContentData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class handles background tasks involving the database
 */

public class OrderDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 4;
    private static final String DATABASE_NAME = "ordersDb.db";

    /**
     * creates a database reference
     * @param context
     */
    public OrderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE = "CREATE TABLE "
                + OrderContract.OrderEntry.TABLE_NAME +" ("
                + OrderContract.OrderEntry._ID  + " INTEGER PRIMARY KEY, "
                + OrderContract.OrderEntry.COLUMN_ORDER_CALORIES + " FLOAT NOT NULL, "
                + OrderContract.OrderEntry.COLUMN_ORDER_TIME + " TEXT NOT NULL);";

       sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    /**
     * called in the event that there is a newer version of the database avaliable than what the user is currently using
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderContract.OrderEntry.TABLE_NAME); //delete the old table and its data for the new one
        onCreate(sqLiteDatabase);

    }
}
