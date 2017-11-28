package csc4330.lsutrition.Activities;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import csc4330.lsutrition.Adapters.UserHistoryAdapter;
import csc4330.lsutrition.OrderContentData.OrderContract;
import csc4330.lsutrition.R;

public class user_history_activity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>{
    RecyclerView recyclerView;
    UserHistoryAdapter adapter;
    private final int ORDER_LOADER_ID = 1;

    /**
        Android System Action called whenever the corresponding layout is inflated (activity launched, phone rotated, ect.)
        Actions taken are all setup required for the app interface to work
        @param savedInstanceState: contains any data relevant to the current session in the event that the view is destroyed and recreated (e.g. the phone is rotated with user data entered)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history_activity);
        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /* Set up for the RecyclerView */
        recyclerView = findViewById(R.id.user_history_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserHistoryAdapter(this);
        recyclerView.setAdapter(adapter);
        /* Calls the overhead Loader manager to handle and begin loading the database */
        getSupportLoaderManager().initLoader(ORDER_LOADER_ID, null, this );

    }
    /**
        Click handler for the bottom nav component User Trends
        @param view - reference to the element clicked
     */
    public void onUserTrendClick(View view) {
        Intent intent = new Intent(user_history_activity.this, User_Trends_Activity.class);
        startActivity(intent);
    }
    /**
        Click handler for the bottom nav Home button
        @param view - reference to the element clicked
     */
    public void onBottomNavHomeClick(View view){
        Intent intent = new Intent(user_history_activity.this, MainActivity.class);
        startActivity(intent);
    }
    /**
        Click handler for the bottom nav component Deals
        @param view - reference to the element clicked
     */
    public void onDealsClick(View view){
        Intent intent = new Intent(user_history_activity.this, Deals_Display.class);
        startActivity(intent);
    }

    /**
     * Overloaded function that handles any setup needed for the Loader
     * @param i - ID of the Loader, in the event more than one is used
     * @param bundle - any bundle of information sent to the loader
     * @return - an anonymous AsyncTaskLoader, which handles background loading
     */
    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int i, final Bundle bundle) {
        return new android.support.v4.content.AsyncTaskLoader<Cursor>(this) {
            Cursor mOrderData = null;

            /**
             * Callback function for whenever a load operation begins
             */
            @Override
            protected void onStartLoading() {
                if(mOrderData != null){
                    deliverResult(mOrderData);//if there was already data loaded(in the event that the activity was navigated away from while loading), load it
                } else {
                    forceLoad();//starts a load command
                }
            }

            /**
             * Actions the AsyncTask performs in the background
             * @return - the Cursor for the query performed in the background
             */
            @Override
            public Cursor loadInBackground() {
                try {
                    return getContentResolver().query(OrderContract.OrderEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /**
             * sets the local Cursor variable to the Cursor that was just loaded
             * @param data the Cursor that was loaded in
             */
            @Override
            public void deliverResult(Cursor data) {
                mOrderData = data;
                super.deliverResult(data);
            }
        };
    }

    /**
     * Callback for when the Loading task is finished, used to tie up loose ends(e.g. close files) and update UI
     * @param loader - a reference to the loader, allowing us to obtain any data we need from it
     * @param data - the Cursor that was loaded in
     */
    @Override
    public void onLoadFinished(android.support.v4.content.Loader loader, Cursor data) {
        adapter.swapCursor(data);//replaces the old Cursor with the new one
    }

    /**
     * Callback for when the loader is reset(in the event of an interruption or such)
     * @param loader - a reference to the loader, to obtain any needed data from it
     */
    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {
        adapter.swapCursor(null);
    }


}
