package csc4330.lsutrition;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class user_history_activity extends AppCompatActivity {
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
}
