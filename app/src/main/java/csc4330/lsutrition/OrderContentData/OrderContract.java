package csc4330.lsutrition.OrderContentData;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Danny on 11/27/2017.
 */

public class OrderContract {
    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "csc4330.lsutrition";
    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_ORDERS = "orders";

    public static final class OrderEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ORDERS).build();



        public static final String TABLE_NAME = "orders";
        public static final String COLUMN_ORDER_CALORIES = "calories";
    }
}
