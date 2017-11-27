package csc4330.lsutrition.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import csc4330.lsutrition.R;

public class Deals_Display extends AppCompatActivity {


    /*
        Android System Action called whenever the corresponding layout is inflated (activity launched, phone rotated, ect.)
        Actions taken are all setup required for the app interface to work
        @param savedInstanceState: contains any data relevant to the current session in the event that the view is destroyed and recreated (e.g. the phone is rotated with user data entered)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals__display);
        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    /*
        Click handler for the bottom nav component User history
        @param view - reference to the element clicked
     */
    public void onUserHistoryClick(View view){
        Intent intent = new Intent(Deals_Display.this, user_history_activity.class);
        startActivity(intent);
    }
    /*
        Click handler for the bottom nav component User Trends
        @param view - reference to the element clicked
     */
    public void onUserTrendClick(View view) {
        Intent intent = new Intent(Deals_Display.this, User_Trends_Activity.class);
        startActivity(intent);
    }
    /*
        Click handler for the bottom nav Home Button
        @param view - reference to the element clicked
     */
    public void onBottomNavHomeClick(View view) {
        Intent intent = new Intent(Deals_Display.this, MainActivity.class);
        startActivity(intent);
    }
}
