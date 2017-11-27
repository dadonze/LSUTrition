package csc4330.lsutrition.OrderContentData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Danny on 11/27/2017.
 */

public class OrderDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 3;
    private static final String DATABASE_NAME = "ordersDb.db";

    public OrderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE = "CREATE TABLE "
                + OrderContract.OrderEntry.TABLE_NAME +" ("
                + OrderContract.OrderEntry._ID  + " INTEGER PRIMARY KEY, "
                + OrderContract.OrderEntry.COLUMN_ORDER_CALORIES + " FLOAT NOT NULL);";

       sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderContract.OrderEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
