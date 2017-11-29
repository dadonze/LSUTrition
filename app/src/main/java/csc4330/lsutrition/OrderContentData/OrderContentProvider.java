package csc4330.lsutrition.OrderContentData;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static csc4330.lsutrition.OrderContentData.OrderContract.OrderEntry.TABLE_NAME;

/**
 * Created by Danny on 11/27/2017.
 */

public class OrderContentProvider extends ContentProvider {

    public final static int ORDERS = 100;
    public final static int ORDERS_WITH_NAME = 101;
    public final static int ORDERS_WITH_CAL = 102;

    private static final UriMatcher orderUriMatcher = buildUriMatcher();
    private OrderDbHelper orderDbHelper;

    public static UriMatcher buildUriMatcher() {

        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the constructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /*
          All paths added to the UriMatcher have a corresponding int.
          For each kind of uri you may want to access, add the corresponding match with addURI.
          The two calls below add matches for the task directory and a single item by ID.
         */
        uriMatcher.addURI(OrderContract.AUTHORITY, OrderContract.PATH_ORDERS, ORDERS);
        uriMatcher.addURI(OrderContract.AUTHORITY, OrderContract.PATH_ORDERS + "/*", ORDERS_WITH_NAME);
        uriMatcher.addURI(OrderContract.AUTHORITY, OrderContract.PATH_ORDERS + "/#", ORDERS_WITH_CAL);

        return uriMatcher;
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        orderDbHelper = new OrderDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = orderDbHelper.getReadableDatabase();

        // Write URI match code and set a variable to return a Cursor
        int match = orderUriMatcher.match(uri);
        Cursor retCursor;

        // Query for the tasks directory and write a default case
        switch (match) {
            // Query for the tasks directory
            case ORDERS:
                retCursor =  db.query(TABLE_NAME,
                        strings,
                        s,
                        strings1,
                        null,
                        null,
                        s1);
                break;
            // Default exception
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Set a notification URI on the Cursor and return that Cursor
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the desired Cursor
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = orderDbHelper.getWritableDatabase();

        // Write URI matching code to identify the match for the tasks directory
        int match = orderUriMatcher.match(uri);
        Uri returnUri; // URI to be returned

        switch (match) {
            case ORDERS:
                // Insert new values into the database
                // Inserting values into tasks table
                long id = db.insert(TABLE_NAME, null, contentValues);
                if ( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(OrderContract.OrderEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            // Set the value for the returnedUri and write the default case for unknown URI's
            // Default case throws an UnsupportedOperationException
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Notify the resolver if the uri has been changed, and return the newly inserted URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return constructed uri (this points to the newly inserted row of data)
        return returnUri;




    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
