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

    /*
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
        recyclerView = findViewById(R.id.user_history_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserHistoryAdapter(this);
        recyclerView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(ORDER_LOADER_ID, null, this );

    }
    /*
        Click handler for the bottom nav component User Trends
        @param view - reference to the element clicked
     */
    public void onUserTrendClick(View view) {
        Intent intent = new Intent(user_history_activity.this, User_Trends_Activity.class);
        startActivity(intent);
    }
    /*
        Click handler for the bottom nav Home button
        @param view - reference to the element clicked
     */
    public void onBottomNavHomeClick(View view){
        Intent intent = new Intent(user_history_activity.this, MainActivity.class);
        startActivity(intent);
    }
    /*
        Click handler for the bottom nav component Deals
        @param view - reference to the element clicked
     */
    public void onDealsClick(View view){
        Intent intent = new Intent(user_history_activity.this, Deals_Display.class);
        startActivity(intent);
    }


    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int i, final Bundle bundle) {
        return new android.support.v4.content.AsyncTaskLoader<Cursor>(this) {
            Cursor mOrderData = null;

            @Override
            protected void onStartLoading() {
                if(mOrderData != null){
                    deliverResult(mOrderData);
                } else {
                    forceLoad();
                }
            }

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

            @Override
            public void deliverResult(Cursor data) {
                mOrderData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {
        adapter.swapCursor(null);
    }


}
